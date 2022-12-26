package com.ssv.binchecker.di.modules

import androidx.lifecycle.ViewModel
import com.ssv.binchecker.di.ViewModelKey
import com.ssv.binchecker.presentation.viewModels.BinInfoViewModel
import com.ssv.binchecker.presentation.viewModels.InitialViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(BinInfoViewModel::class)
    @Binds
    fun bindBinInfoViewModel(impl: BinInfoViewModel): ViewModel

    @IntoMap
    @ViewModelKey(InitialViewModel::class)
    @Binds
    fun bindInitialViewModel(impl: InitialViewModel): ViewModel
}