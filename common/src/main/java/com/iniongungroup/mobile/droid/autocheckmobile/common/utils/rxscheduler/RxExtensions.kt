package com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

fun <T> Observable<T>.subscribeOnIoObserveOnUi(scheduler: SchedulerProvider, onNext: (T) -> Unit, onError: ((Throwable) -> Unit)? = null): Disposable {
    return subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
        .subscribe({
            onNext(it)
        }, { throwable ->
            onError?.apply {
                this(throwable)
            }
        })
}

fun <T> Single<T>.subscribeOnIoObserveOnUi(scheduler: SchedulerProvider, onSuccess: (T) -> Unit, onError: ((Throwable) -> Unit)? = null): Disposable {
    return subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
        .subscribe({
            onSuccess(it)
        }, { throwable ->
            onError?.apply {
                this(throwable)
            }
        })
}

fun Completable.subscribeOnIoObserveOnUi(scheduler: SchedulerProvider, onComplete: (() -> Unit), onError: ((Throwable) -> Unit)? = null): Disposable {
    return subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
        .subscribe({
            onComplete()
        }, { throwable ->
            onError?.apply {
                this(throwable)
            }
        })
}