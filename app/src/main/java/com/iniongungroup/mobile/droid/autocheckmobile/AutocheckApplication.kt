package com.iniongungroup.mobile.droid.autocheckmobile

import com.facebook.drawee.backends.pipeline.Fresco
import com.iniongungroup.mobile.droid.autocheckmobile.di.component.AppComponent
import com.iniongungroup.mobile.droid.autocheckmobile.di.component.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Created by Isaac Iniongun on 22/06/2020.
 * For Food App project.
 */

class AutocheckApplication : DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

        AndroidThreeTen.init(this)

        Fresco.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        appComponent = DaggerAppComponent.builder().bindAppInstance(this).buildAppComponent()

        return appComponent

    }
}