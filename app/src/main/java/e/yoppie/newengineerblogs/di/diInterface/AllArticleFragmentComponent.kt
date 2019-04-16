package e.yoppie.newengineerblogs.di.diInterface

import dagger.Component
import e.yoppie.newengineerblogs.di.diModule.AllArticleFragmentModule
import e.yoppie.newengineerblogs.view.fragment.AllArticleFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AllArticleFragmentModule::class])
interface AllArticleFragmentComponent {
    fun inject(allArticleFragment: AllArticleFragment)
}