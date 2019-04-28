package e.yoppie.newengineerblogs.di.diInterface

import dagger.Component
import e.yoppie.newengineerblogs.di.diModule.FavoriteArticleViewModelModule
import e.yoppie.newengineerblogs.viewmodel.FavoriteArticleViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [FavoriteArticleViewModelModule::class])
interface FavoriteArticleViewModelComponent {
    fun inject(favoriteArticleViewModel: FavoriteArticleViewModel)
}