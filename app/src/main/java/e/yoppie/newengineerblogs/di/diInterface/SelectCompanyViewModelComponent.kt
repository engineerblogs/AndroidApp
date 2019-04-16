package e.yoppie.newengineerblogs.di.diInterface

import dagger.Component
import e.yoppie.newengineerblogs.di.diModule.SelectCompanyViewModelModule
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [SelectCompanyViewModelModule::class])
interface SelectCompanyViewModelComponent {
    fun inject(selectCompanyViewModel: SelectCompanyViewModel)
}