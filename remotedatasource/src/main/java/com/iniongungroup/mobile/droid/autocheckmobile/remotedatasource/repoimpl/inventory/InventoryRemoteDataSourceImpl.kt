package com.iniongungroup.mobile.droid.autocheckmobile.remotedatasource.repoimpl.inventory

import com.iniongungroup.mobile.droid.autocheckmobile.remotedatasource.services.InventoryService
import com.iniongungroup.mobile.droid.autocheckmobile.repository.remotedatasource.inventory.IInventoryRemoteDataSource
import retrofit2.Retrofit
import javax.inject.Inject

class InventoryRemoteDataSourceImpl @Inject constructor(
    retrofit: Retrofit
): IInventoryRemoteDataSource {

    private val inventoryService = retrofit.create(InventoryService::class.java)

    override fun getCarBrands() = inventoryService.getCarBrands()

    override fun getCars(page_number: Int, page_size: Int) = inventoryService.getCars(page_number, page_size)

    override fun getCarDetails(id: String) = inventoryService.getCarDetails(id)

    override fun getCarMedia(id: String) = inventoryService.getCarMedia(id)
}