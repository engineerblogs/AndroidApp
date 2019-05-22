package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import e.yoppie.newengineerblogs.model.data.Article
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.assertj.core.api.Assertions.*

@RunWith(RobolectricTestRunner::class)
class FavoriteArticleViewModelTest {
    private lateinit var favoriteArticleViewModel: FavoriteArticleViewModel

    @Before
    fun setUp() {
        favoriteArticleViewModel = FavoriteArticleViewModel()
    }

    @Test
    fun getFavoriteArticleListLiveData() {
        val articleList = mutableListOf(
                Article("100", "title", "thumbnail", "url", "publishedDate", "author")
        )
        val mutableLiveData = MutableLiveData<MutableList<Article>>()
        mutableLiveData.value = articleList

        assertThat(favoriteArticleViewModel.favoriteArticleListLiveData.value).isEqualTo(mutableLiveData.value)
    }

    @Test
    fun getFavoriteArticleList() {
        val articleList = mutableListOf(
                Article("100", "title", "thumbnail", "url", "publishedDate", "author")
        )

        assertThat(favoriteArticleViewModel.favoriteArticleList).isEqualTo(articleList)
    }
}