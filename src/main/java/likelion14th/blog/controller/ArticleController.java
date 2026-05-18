package likelion14th.blog.controller;

import likelion14th.blog.dto.request.ArticleRequest;
import likelion14th.blog.dto.request.UpdateArticleRequest;
import likelion14th.blog.dto.response.ApiResponse;
import likelion14th.blog.dto.response.ArticleResponse;
import likelion14th.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping()
    public ResponseEntity<ApiResponse<ArticleResponse>> addArticle(@RequestBody ArticleRequest request){
        ArticleResponse articleResponse =
                articleService.addArticle(request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(201, "게시글 생성에 성공하였습니다.", articleResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleResponse>> getOneArticle(@PathVariable Long id){
        ArticleResponse articleResponse = articleService.getOneArticle(id);

        return ResponseEntity.ok(ApiResponse.success(200, "게시글 개별 조회에 성공하였습니다.", articleResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleResponse>> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        ArticleResponse articleResponse = articleService.updateArticle(id, request.getTitle(), request.getContent());

        return ResponseEntity.ok(ApiResponse.success(200, "게시글을 업데이트 하였습니다.", articleResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle((id));

        return ResponseEntity.ok(ApiResponse.success(204, "게시글을 삭제하는데 성공하였습니다."));
    }
}
