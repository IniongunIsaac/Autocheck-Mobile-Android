package com.iniongungroup.mobile.droid.autocheckmobile.repository.inventory

import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarDetail
import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarMediaResponse
import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarResponse
import com.iniongungroup.mobile.droid.autocheckmobile.entities.MakeResponse
import io.reactivex.Observable

interface IInventoryRepo {

    fun getCarBrands(): Observable<MakeResponse>

    fun getCars(page_number: Int, page_size: Int): Observable<CarResponse>

    fun getCarDetails(id: String): Observable<CarDetail>

    fun getCarMedia(id: String): Observable<CarMediaResponse>

}