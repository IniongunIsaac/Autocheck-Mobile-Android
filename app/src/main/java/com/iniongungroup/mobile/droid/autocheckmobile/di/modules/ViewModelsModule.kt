package com.iniongungroup.mobile.droid.autocheckmobile.di.modules

import androidx.lifecycle.ViewModelProvider
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.di.keys.AppViewModelKey
import com.iniongungroup.mobile.droid.autocheckmobile.presentation.StartActivityViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.viewmodelfactory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @AppViewModelKey(StartActivityViewModel::class)
    abstract fun bindStartActivityViewModel(
        viewModel: StartActivityViewModel
    ): BaseViewModel

}