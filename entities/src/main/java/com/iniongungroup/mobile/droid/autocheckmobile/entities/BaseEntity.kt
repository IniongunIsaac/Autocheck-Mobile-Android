package com.iniongungroup.mobile.droid.autocheckmobile.entities

import androidx.room.Ignore
import java.util.*

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

abstract class BaseEntity {
    @Ignore val uniqueKey: String = UUID.randomUUID().toString()
    abstract override fun equals(other: Any?): Boolean
}