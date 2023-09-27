package spring.web.knit.web.dto;

import lombok.Getter;
import spring.web.knit.domain.posts.Posts;
import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id=entity.getId();
        this.title= entity.getTitle();
        this.author=entity.getAuthor();
        this.modifiedDate=entity.getModifiedDate();
    }
}