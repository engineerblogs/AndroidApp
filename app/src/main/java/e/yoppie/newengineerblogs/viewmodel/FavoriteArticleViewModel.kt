package e.yoppie.newengineerblogs.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import e.yoppie.newengineerblogs.di.diInterface.DaggerFavoriteArticleViewModelComponent
import e.yoppie.newengineerblogs.di.diModule.FavoriteArticleViewModelModule
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.repository.FavoriteArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    @SuppressLint("CheckResult")
    fun loadFirstFavoriteArticleList() {
        favoriteArticleRepository.getFavoriteArticles("yoppie")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    val newFavoriteArticleList: MutableList<Article> = mutableListOf()
                    res.categories.forEach {
                        newFavoriteArticleList.addAll(it.articles)
                    }
                    favoriteArticleList = newFavoriteArticleList
                    favoriteArticleListLiveData.postValue(favoriteArticleList)
                }, { error ->
                    Log.d("yoppie_debug", error.message)
                })
    }
}