package e.yoppie.newengineerblogs.repository

import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ArticleRepositoryTest {
    private lateinit var articleRepository: ArticleRepository

    @Before
    fun setUp() {
        articleRepository = ArticleRepository()
    }

    @Test
    fun getAllCategoryArticles() {
    }

    @Test
    fun getCategoryArticles() {
    }
}