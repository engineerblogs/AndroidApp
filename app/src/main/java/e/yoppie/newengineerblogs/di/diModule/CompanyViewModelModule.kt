package e.yoppie.newengineerblogs.di.diModule

import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import e.yoppie.newengineerblogs.model.data.Article
import e.yoppie.newengineerblogs.model.data.Category
import javax.inject.Singleton

@Module
class CompanyViewModelModule {

    @Singleton
    @Provides
    fun provideCategoryListData(): MutableLiveData<MutableList<Category>>{
        val mutableLiveData = MutableLiveData<MutableList<Category>>()
        val articles = mutableListOf(
                Article("100", "title", "thumbnail", "url", "publishedDate", "author")
        )
        mutableLiveData.value = mutableListOf(
                Category("yoppie", "100", articles)
        )
        return mutableLiveData
    }
}