package com.iniongungroup.mobile.droid.autocheckmobile.repository.inventory

import com.iniongungroup.mobile.droid.autocheckmobile.repository.remotedatasource.inventory.IInventoryRemoteDataSource
import javax.inject.Inject

class InventoryRepoImpl @Inject constructor(
    private val inventoryRemoteDataSource: IInventoryRemoteDataSource
) : IInventoryRepo {

    override fun getCarBrands() = inventoryRemoteDataSource.getCarBrands()

    override fun getCars(page_number: Int, page_size: Int) = inventoryRemoteDataSource.getCars(page_number, page_size)

    override fun getCarDetails(id: String) = inventoryRemoteDataSource.getCarDetails(id)

    override fun getCarMedia(id: String) = inventoryRemoteDataSource.getCarMedia(id)

}