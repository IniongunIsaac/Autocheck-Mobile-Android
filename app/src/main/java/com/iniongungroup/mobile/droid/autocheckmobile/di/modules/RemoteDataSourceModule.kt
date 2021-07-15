package com.iniongungroup.mobile.droid.autocheckmobile.di.modules

import com.iniongungroup.mobile.droid.autocheckmobile.di.scopes.AppScope
import com.iniongungroup.mobile.droid.autocheckmobile.remotedatasource.repoimpl.inventory.InventoryRemoteDataSourceImpl
import com.iniongungroup.mobile.droid.autocheckmobile.repository.remotedatasource.inventory.IInventoryRemoteDataSource
import dagger.Binds
import dagger.Module

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

@Module
abstract class RemoteDataSourceModule {

    @Binds
    @AppScope
    internal abstract fun bindInventoryRemoteDataSource(
        remoteDataSource: InventoryRemoteDataSourceImpl
    ): IInventoryRemoteDataSource

}