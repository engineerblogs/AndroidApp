package e.yoppie.newengineerblogs.service

import e.yoppie.newengineerblogs.model.data.Article
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DiffArticleCallbackTest {
    lateinit var diffArticleCallback: DiffArticleCallback

    private val oldArticles = mutableListOf(
            Article("0", "title_0", "thumbnail_0", "url_0", "publishedDate_0", "author_0"),
            Article("1", "title_1", "thumbnail_1", "url_1", "publishedDate_1", "author_1"),
            Article("2", "title_2", "thumbnail_2", "url_2", "publishedDate_2", "author_2")
    )

    private val newArticles = mutableListOf(
            Article("3", "title_3", "thumbnail_3", "url_3", "publishedDate_3", "author_3"),
            Article("4", "title_4", "thumbnail_4", "url_4", "publishedDate_4", "author_4"),
            Article("2", "title_2", "thumbnail_2", "url_2", "publishedDate_2", "author_2")
    )

    @Before
    fun setUp() {
        diffArticleCallback = DiffArticleCallback(oldArticles, newArticles)
    }

    @Test
    fun areContentsTheSame() {
        assertThat(diffArticleCallback.areContentsTheSame(0, 1)).isFalse()
        assertThat(diffArticleCallback.areContentsTheSame(2, 2)).isTrue()
    }

    @Test
    fun areItemsTheSame() {
        assertThat(diffArticleCallback.areItemsTheSame(0, 1)).isFalse()
        assertThat(diffArticleCallback.areItemsTheSame(2, 2)).isTrue()
    }

    @Test
    fun getNewListSize() {
        assertThat(diffArticleCallback.newListSize).isEqualTo(3)
    }

    @Test
    fun getOldListSize() {
        assertThat(diffArticleCallback.oldListSize).isEqualTo(3)
    }
}