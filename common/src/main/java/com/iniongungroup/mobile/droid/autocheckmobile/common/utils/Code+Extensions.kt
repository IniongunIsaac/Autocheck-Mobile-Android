package com.iniongungroup.mobile.droid.autocheckmobile.common.utils

import android.text.TextUtils
import android.view.View
import java.text.NumberFormat
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

fun runAfter(delay: Long = 500, block: () -> Unit) {
    Timer().schedule(delay) {
        block.invoke()
    }
}

fun String.isValidEmailAddress(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPhoneNumber(): Boolean {
    return matches("^[0-9]*$".toRegex()) && length == 11
}

fun String.isValidName(): Boolean {
    return matches("^[a-zA-Z ]*$".toRegex()) && isNotEmpty()
}

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun Any?.isNotNull(): Boolean {
    return this != null
}

fun Number.currencyFormatted(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "NG"))
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("NGN")
    return format.format(this).replace("NGN", "₦")
}

fun Number.formatted(decimalPlaces: Int = 2) = String.format("%.${decimalPlaces}f", this)