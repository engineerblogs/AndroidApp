package e.yoppie.newengineerblogs.di.diInterface

import dagger.Component
import e.yoppie.newengineerblogs.di.diModule.CategoryFragmentModule
import e.yoppie.newengineerblogs.view.fragment.CategoryFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [CategoryFragmentModule::class])
interface CategoryFragmentComponent {
    fun inject(categoryFragment: CategoryFragment)
}