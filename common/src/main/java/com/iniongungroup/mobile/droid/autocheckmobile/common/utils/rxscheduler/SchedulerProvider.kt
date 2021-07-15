package com.iniongungroup.mobile.droid.autocheckmobile.common.utils.rxscheduler

import io.reactivex.Scheduler

/**
 * Created by Isaac Iniongun on 22/06/2020.
 * For News App project.
 */

interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler

}