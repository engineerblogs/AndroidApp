package e.yoppie.newengineerblogs.di.diModule

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import e.yoppie.newengineerblogs.view.fragment.CategoryFragment
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel
import javax.inject.Singleton

@Module
class CategoryFragmentModule(private val categoryViewModel: CompanyViewModel, private val position: Int, private val categoryFragment: CategoryFragment) {

    @Singleton
    @Provides
    fun provideCompanyId(): String {
        return categoryViewModel.categoryListData.value!![position].id
    }

    @Singleton
    @Provides
    fun provideArticleViewModel(): ArticleViewModel {
        return ViewModelProviders.of(categoryFragment).get(position.toString(), ArticleViewModel::class.java)
    }
}