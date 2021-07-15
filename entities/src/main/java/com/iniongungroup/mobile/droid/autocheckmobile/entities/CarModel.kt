package com.iniongungroup.mobile.droid.autocheckmobile.entities

data class CarModel(
    val modelFeatures: List<Any>,
    val id: Int,
    val name: String,
    val wheelType: String,
    val make: CarBodyType,
    val popular: Boolean
)