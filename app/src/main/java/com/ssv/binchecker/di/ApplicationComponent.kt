package com.ssv.binchecker.di

import android.app.Application
import com.ssv.binchecker.BinApp
import com.ssv.binchecker.di.modules.DataModule
import com.ssv.binchecker.di.modules.DatabaseModule
import com.ssv.binchecker.di.modules.NetworkModule
import com.ssv.binchecker.di.modules.ViewModelModule
import com.ssv.binchecker.presentation.MainActivity
import com.ssv.binchecker.presentation.fragments.BinInfoFragment
import com.ssv.binchecker.presentation.fragments.InitialFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@ApplicationScope
@Component(modules = [ViewModelModule::class, DataModule::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun injectApplication(app: BinApp)
    fun injectInitialFragment(initialFragment: InitialFragment)
    fun injectBinInfoFragment(binInfoFragment: BinInfoFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance app: Application
        ):ApplicationComponent
    }
}