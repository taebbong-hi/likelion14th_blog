package likelion14th.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor /*없으면 오류 뜸*/
@AllArgsConstructor
public class ArticleRequest {
    private String title;
    private String content;
    private String author;
    private String password;
}
