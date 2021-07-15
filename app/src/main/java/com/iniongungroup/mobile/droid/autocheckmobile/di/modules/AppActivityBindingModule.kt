package com.iniongungroup.mobile.droid.autocheckmobile.di.modules

import com.iniongungroup.mobile.droid.autocheckmobile.di.scopes.PerActivity
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.InventoryActivity
import com.iniongungroup.mobile.droid.autocheckmobile.presentation.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

@Module
abstract class AppActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindStartActivity(): StartActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindInventoryActivity(): InventoryActivity

}