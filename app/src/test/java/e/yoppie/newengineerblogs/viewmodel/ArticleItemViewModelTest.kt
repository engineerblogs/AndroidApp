package e.yoppie.newengineerblogs.viewmodel

import e.yoppie.newengineerblogs.model.data.Article
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ArticleItemViewModelTest {

    @Test
    fun setArticle() {
        val articleItemViewModel = ArticleItemViewModel()
        val article = Article("id", "title", "thumbnail", "url", "publishedDate", "author")
        articleItemViewModel.setArticle(article)
        assertThat(articleItemViewModel.id.value)
                .isEqualTo(article.id)
        assertThat(articleItemViewModel.title.value)
                .isEqualTo(article.title)
        assertThat(articleItemViewModel.thumbnail.value)
                .isEqualTo(article.thumbnail)
        assertThat(articleItemViewModel.url.value)
                .isEqualTo(article.url)
        assertThat(articleItemViewModel.publishedDate.value)
                .isEqualTo(article.publishedDate)
        assertThat(articleItemViewModel.author.value)
                .isEqualTo(article.author)
    }
}