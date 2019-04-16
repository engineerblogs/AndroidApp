package e.yoppie.newengineerblogs.di.diInterface

import dagger.Component
import e.yoppie.newengineerblogs.di.diModule.CompanyViewModelModule
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [CompanyViewModelModule::class])
interface CompanyViewModelComponent {
    fun inject(companyViewModel: CompanyViewModel)
}