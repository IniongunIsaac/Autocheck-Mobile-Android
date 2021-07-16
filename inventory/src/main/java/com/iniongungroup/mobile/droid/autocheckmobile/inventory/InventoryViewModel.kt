package com.iniongungroup.mobile.droid.autocheckmobile.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.livedataevent.LiveDataEvent
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler.SchedulerProvider
import com.iniongungroup.mobile.droid.autocheckmobile.entities.Car
import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarDetail
import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarMedia
import com.iniongungroup.mobile.droid.autocheckmobile.entities.Make
import com.iniongungroup.mobile.droid.autocheckmobile.repository.inventory.IInventoryRepo
import io.reactivex.Observable
import javax.inject.Inject

typealias CarsAndMakes = Pair<List<Car>, List<Make>>
typealias CarDetailsAndMedia = Pair<CarDetail, List<CarMedia>>

class InventoryViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val inventoryRepo: IInventoryRepo
): BaseViewModel() {

    private val _carsAndMakes = MutableLiveData<CarsAndMakes>()
    val carsAndMakes: LiveData<CarsAndMakes> = _carsAndMakes

    private val _showCarDetails = MutableLiveData<LiveDataEvent<Boolean>>()
    val showCarDetails: LiveData<LiveDataEvent<Boolean>> = _showCarDetails

    lateinit var carDetailsAndMedia: CarDetailsAndMedia

    private val _carMediaURL = MutableLiveData<LiveDataEvent<String>>()
    val carMediaURL: LiveData<LiveDataEvent<String>> = _carMediaURL

    init {
        getCarsAndMakes()
    }

    private fun getCarsAndMakes() {
        subscribeAny(
            Observable.zip(
                inventoryRepo.getCars(1, 30),
                inventoryRepo.getCarBrands(),
                { carsRes, makesRes -> Pair(carsRes, makesRes)}
            ), schedulerProvider, "Unable to get data!", {
                _carsAndMakes.value = Pair(it.first.result, it.second.makeList)
            }
        )
    }

    fun getCarDetailsAndMedia(carId: String) {
        subscribeAny(
            Observable.zip(
                inventoryRepo.getCarDetails(carId),
                inventoryRepo.getCarMedia(carId),
                { carDetailRes, mediaRes -> Pair(carDetailRes, mediaRes)}
            ), schedulerProvider, "Unable to get details, please try again!", {
                carDetailsAndMedia = Pair(it.first, it.second.carMediaList)
                _showCarDetails.value = LiveDataEvent(true)
            }
        )
    }

    fun handleCarMediaClicked(media: CarMedia) {
        _carMediaURL.value = LiveDataEvent(media.imageURL)
    }

}