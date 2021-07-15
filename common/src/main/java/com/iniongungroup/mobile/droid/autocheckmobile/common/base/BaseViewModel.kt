package com.iniongungroup.mobile.droid.autocheckmobile.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.isNotNull
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.livedataevent.LiveDataEvent
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler.SchedulerProvider
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler.subscribeOnIoObserveOnUi
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.state.AppResult
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.state.AppState
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.state.AppState.FAILED
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.state.AppState.SUCCESS
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Isaac Iniongun on 22/06/2020.
 * For News App project.
 */

abstract class BaseViewModel: ViewModel() {

    private val _notificationLiveData = MutableLiveData<LiveDataEvent<AppResult<Unit>>>()
    val notificationLiveData = _notificationLiveData as LiveData<LiveDataEvent<AppResult<Unit>>>

    private val compositeDisposable = CompositeDisposable()

    fun showLoading() {
        _notificationLiveData.value = LiveDataEvent(AppResult.loading())
    }

    fun hideLoading() {
        _notificationLiveData.value = LiveDataEvent(AppResult.success())
    }

    fun showMessage(message: String, messageType: AppState = SUCCESS) {
        when(messageType) {
            FAILED -> _notificationLiveData.value = LiveDataEvent(AppResult.failed(message))
            SUCCESS -> _notificationLiveData.value = LiveDataEvent(AppResult.success(message = message))
        }
    }

    fun subscribeAny(observable: Completable,
                        scheduler: SchedulerProvider,
                        errorMessage: String? = null,
                        success: () -> Unit,
                        error: ((Throwable) -> Unit)? = null) {
        showLoading()
        compositeDisposable.add(
            observable.subscribeOnIoObserveOnUi(scheduler, {
                hideLoading()
                success()
            }, {
                hideLoading()
                if (error != null)
                    error(it)
                else {
                    showMessage(it.localizedMessage, FAILED)
                }
            })
        )
    }

    fun <T>subscribeAny(observable: Observable<T>,
                     scheduler: SchedulerProvider,
                     errorMessage: String? = null,
                     success: (T) -> Unit,
                     error: ((Throwable) -> Unit)? = null) {
        showLoading()
        compositeDisposable.add(
            observable.subscribeOnIoObserveOnUi(scheduler, {
                hideLoading()
                success(it)
            }, {
                hideLoading()
                if (error.isNotNull())
                    error(it)
                else {
                    showMessage(it.localizedMessage, FAILED)
                }
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}