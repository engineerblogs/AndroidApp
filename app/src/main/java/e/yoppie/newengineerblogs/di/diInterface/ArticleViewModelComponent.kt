package e.yoppie.newengineerblogs.di.diInterface

import dagger.Component
import e.yoppie.newengineerblogs.di.diModule.ArticleViewModelModule
import e.yoppie.newengineerblogs.viewmodel.ArticleViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ArticleViewModelModule::class])
interface ArticleViewModelComponent {
    fun inject(viewModel: ArticleViewModel)
}