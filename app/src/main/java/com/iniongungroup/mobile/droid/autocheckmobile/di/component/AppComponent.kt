package com.iniongungroup.mobile.droid.autocheckmobile.di.component

import com.iniongungroup.mobile.droid.autocheckmobile.AutocheckApplication
import com.iniongungroup.mobile.droid.autocheckmobile.di.modules.*
import com.iniongungroup.mobile.droid.autocheckmobile.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Isaac Iniongun on 22/06/2020.
 * For News App project.
 */

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        RemoteDataSourceModule::class,
        RepositoryModule::class,
        ViewModelsModule::class,
        AppActivityBindingModule::class,
        AppFragmentBindingModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(autocheckApp: AutocheckApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindAppInstance(autocheckApp: AutocheckApplication): Builder

        fun buildAppComponent(): AppComponent

    }

}