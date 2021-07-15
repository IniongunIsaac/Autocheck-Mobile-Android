package com.iniongungroup.mobile.droid.autocheckmobile.remotedatasource.services

import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarDetail
import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarMediaResponse
import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarResponse
import com.iniongungroup.mobile.droid.autocheckmobile.entities.MakeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InventoryService {

    @GET("make")
    fun getCarBrands(@Query("popular") popular: Boolean = true): Observable<MakeResponse>

    @GET("car/search")
    fun getCars(@Query("page_number") page_number: Int, @Query("page_size") page_size: Int): Observable<CarResponse>

    @GET("car/{id}")
    fun getCarDetails(@Path("id") id: String): Observable<CarDetail>

    @GET("car_media")
    fun getCarMedia(@Query("carId") id: String): Observable<CarMediaResponse>

}