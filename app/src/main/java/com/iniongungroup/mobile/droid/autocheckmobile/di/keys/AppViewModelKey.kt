package com.iniongungroup.mobile.droid.autocheckmobile.di.keys

import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Isaac Iniongun on 22/06/2020.
 * For News App project.
 */

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class AppViewModelKey (val value: KClass<out BaseViewModel>)