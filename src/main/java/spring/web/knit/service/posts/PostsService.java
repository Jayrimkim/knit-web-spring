package spring.web.knit.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.web.knit.domain.posts.Posts;
import spring.web.knit.domain.posts.PostsRepository;
import spring.web.knit.web.dto.PostsListResponseDto;
import spring.web.knit.web.dto.PostsResponseDto;
import spring.web.knit.web.dto.PostsSaveRequestDto;
import spring.web.knit.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    //목록출력
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }
}
