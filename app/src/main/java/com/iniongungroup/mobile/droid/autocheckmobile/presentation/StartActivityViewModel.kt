package com.iniongungroup.mobile.droid.autocheckmobile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.livedataevent.LiveDataEvent
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.runAfter
import javax.inject.Inject

class StartActivityViewModel @Inject constructor(
): BaseViewModel() {

    private val _onBoardingItemsViewPagerPosition = MutableLiveData(0)
    val onBoardingItemsViewPagerPosition: LiveData<Int> = _onBoardingItemsViewPagerPosition

    private val _showInventory = MutableLiveData<LiveDataEvent<Boolean>>()
    val showInventory: LiveData<LiveDataEvent<Boolean>> = _showInventory

    init {
        runAfter(2000) {
            _showInventory.postValue(LiveDataEvent(true))
        }
    }

}