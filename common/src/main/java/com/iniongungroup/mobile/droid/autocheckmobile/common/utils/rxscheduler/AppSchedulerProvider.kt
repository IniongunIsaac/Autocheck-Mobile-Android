package com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Isaac Iniongun on 22/06/2020.
 * For News App project.
 */

class AppSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()
}