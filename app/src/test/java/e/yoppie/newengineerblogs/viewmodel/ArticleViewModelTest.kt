package e.yoppie.newengineerblogs.viewmodel

import e.yoppie.newengineerblogs.model.data.Article
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.assertj.core.api.Assertions.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ArticleViewModelTest {
    private lateinit var articleViewModel: ArticleViewModel

    @Before
    fun setUp() {
        articleViewModel = ArticleViewModel()
    }

    @Test
    fun set() {
        val articleList = mutableListOf(
                Article("0", "title_0", "thumbnail_0", "url_0", "publishedDate_0", "author_0"),
                Article("1", "title_1", "thumbnail_1", "url_1", "publishedDate_1", "author_1"),
                Article("2", "title_2", "thumbnail_2", "url_2", "publishedDate_2", "author_2")
        )
        articleViewModel.set(articleList)
        assertThat(articleViewModel.articleListLiveData.value).containsAll(articleList)
        assertThat(articleViewModel.articleList).containsAll(articleList)
    }
}