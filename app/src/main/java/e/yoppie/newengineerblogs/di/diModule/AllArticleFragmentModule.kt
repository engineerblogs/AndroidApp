package e.yoppie.newengineerblogs.di.diModule

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import e.yoppie.newengineerblogs.viewmodel.CompanyViewModel
import javax.inject.Singleton

@Module
class AllArticleFragmentModule(private val fragmentActivity: FragmentActivity?) {

    @Singleton
    @Provides
    fun provideCompanyViewModel(): CompanyViewModel{
        return ViewModelProviders.of(fragmentActivity!!).get(CompanyViewModel::class.java)
    }
}