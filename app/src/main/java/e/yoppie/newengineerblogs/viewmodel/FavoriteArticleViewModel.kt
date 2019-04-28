package e.yoppie.newengineerblogs.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newengineerblogs.di.diInterface.DaggerFavoriteArticleViewModelComponent
import e.yoppie.newengineerblogs.di.diModule.FavoriteArticleViewModelModule
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.repository.FavoriteArticleRepository

@Suppress("DEPRECATION")
class FavoriteArticleViewModel : ViewModel() {

    lateinit var favoriteArticleListLiveData: MutableLiveData<MutableList<Article>>
    lateinit var favoriteArticleList: MutableList<Article>
    private var favoriteArticleRepository = FavoriteArticleRepository()

    init {
        val favoriteArticleViewModelComponent = DaggerFavoriteArticleViewModelComponent
                .builder()
                .favoriteArticleViewModelModule(FavoriteArticleViewModelModule())
                .build()
        favoriteArticleViewModelComponent.inject(this)
    }
}