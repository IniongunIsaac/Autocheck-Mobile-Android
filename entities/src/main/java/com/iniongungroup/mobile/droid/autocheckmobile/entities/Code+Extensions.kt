package com.iniongungroup.mobile.droid.autocheckmobile.entities

import java.text.NumberFormat
import java.util.*

fun Number.currencyFormatted(): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("NGN")
    return format.format(this).replace("NGN", "₦")
}