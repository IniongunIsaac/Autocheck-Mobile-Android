package com.iniongungroup.mobile.droid.autocheckmobile.entities

data class Car(
    val id: String,
    val title: String,
    val imageUrl: String,
    val year: Int,
    val city: String,
    val state: String,
    val gradeScore: Double?,
    val sellingCondition: String?,
    val hasWarranty: Boolean,
    val marketplacePrice: Int,
    val marketplaceOldPrice: Int,
    val hasFinancing: Boolean,
    val mileage: Int,
    val mileageUnit: String,
    val installment: Double,
    val depositReceived: Boolean,
    val loanValue: Int,
    val websiteUrl: String,
    val stats: CarStats,
    val bodyTypeId: String,
    val sold: Boolean,
    val hasThreeDImage: Boolean
): BaseEntity() {
    val priceString: String
        get() = marketplacePrice.currencyFormatted()
}