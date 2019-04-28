package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.di.diInterface.DaggerFavoriteArticleViewModelComponent
import e.yoppie.newengineerblogs.di.diModule.FavoriteArticleViewModelModule
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.repository.FavoriteArticleRepository
import javax.inject.Inject

@Suppress("DEPRECATION")
class FavoriteArticleViewModel : ViewModel() {
    @Inject
    lateinit var favoriteArticleListLiveData: MutableLiveData<MutableList<Article>>

    @Inject
    lateinit var favoriteArticleList: MutableList<Article>

    private var favoriteArticleRepository = FavoriteArticleRepository()

    init {
        val favoriteArticleViewModelComponent = DaggerFavoriteArticleViewModelComponent
                .builder()
                .favoriteArticleViewModelModule(FavoriteArticleViewModelModule())
                .build()
        favoriteArticleViewModelComponent.inject(this)
    }

    fun loadFirstFavoriteArticleList(){

    }
}