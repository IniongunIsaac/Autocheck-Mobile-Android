package com.iniongungroup.mobile.droid.autocheckmobile.entities

data class CarMedia(
    val url: String?,
    val mediaType: String?,
    val name: String?,
    val createdAt: String?,
    val type: String?,
    val id: Int?
): BaseEntity() {
    val imageURL: String
        get() = url ?: ""
}