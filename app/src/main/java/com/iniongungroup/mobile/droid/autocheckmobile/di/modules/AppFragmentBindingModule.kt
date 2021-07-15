package com.iniongungroup.mobile.droid.autocheckmobile.di.modules

import com.iniongungroup.mobile.droid.autocheckmobile.di.scopes.PerFragment
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.fragments.CarDetailsFragment
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.fragments.CarsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppFragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindCarsFragment(): CarsFragment

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindCarDetailsFragment(): CarDetailsFragment

}