package likelion14th.blog.service;

import likelion14th.blog.domain.Article;
import likelion14th.blog.dto.response.ArticleDetailResponse;
import likelion14th.blog.exception.ArticleNotFoundException;
import likelion14th.blog.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    @Test
    @DisplayName("게시글 생성 성공")
    void addArticle_success() {
        // given
        String title = "부다페스트";
        String content = "부다페스트에 가고 싶다.";
        String author = "최성민";
        String password = "0411";

        Article article = new Article(title, content, author, password);

        given(articleRepository.save(any(Article.class)))
                .willReturn(article);

        ArticleDetailResponse response = articleService.addArticle(title, content, author, password);

        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo("부다페스트");
        assertThat(response.getContent()).isEqualTo("부다페스트에 가고 싶다.");
        assertThat(response.getAuthor()).isEqualTo("최성민");
    }

    @Test
    @DisplayName("게시글 조회 실패 - 존재하지 않는 ID")
    void getOneArticle_fail_notFound() {

        Long articleId = 1002L;

        given(articleRepository.findById(articleId))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.getOneArticle(articleId))
                .isInstanceOf(ArticleNotFoundException.class)
                .hasMessage("해당 ID의 게시글을 찾을 수 없습니다.");
    }
}