package com.iniongungroup.mobile.droid.autocheckmobile.entities

data class InspectionItem(
    val medias: List<CarMedia>,
    val name: String,
    val response: String,
    val comment: String?,
    val condition: String?
)