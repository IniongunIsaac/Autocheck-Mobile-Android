package com.iniongungroup.mobile.droid.autocheckmobile.di.modules

import com.iniongungroup.mobile.droid.autocheckmobile.di.scopes.AppScope
import com.iniongungroup.mobile.droid.autocheckmobile.repository.inventory.IInventoryRepo
import com.iniongungroup.mobile.droid.autocheckmobile.repository.inventory.InventoryRepoImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

@Module
abstract class RepositoryModule {

    @Binds
    @AppScope
    internal abstract fun bindInventoryRepo(
        repo: InventoryRepoImpl
    ): IInventoryRepo

}