package com.iniongungroup.mobile.droid.autocheckmobile.di.modules

import android.content.Context
import com.google.gson.GsonBuilder
import com.iniongungroup.mobile.droid.autocheckmobile.AutocheckApplication
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler.AppSchedulerProvider
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler.SchedulerProvider
import com.iniongungroup.mobile.droid.autocheckmobile.di.scopes.AppScope
import com.iniongungroup.mobile.droid.autocheckmobile.utils.AppConstants
import dagger.Module
import dagger.Provides

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideContext(autocheckApp: AutocheckApplication): Context = autocheckApp

    @Provides
    @AppScope
    fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create()

    @Provides
    @AppScope
    fun providesSharedPreference(context: Context) = context.getSharedPreferences(
        AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Provides
    @AppScope
    fun providesSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}