package com.iniongungroup.mobile.droid.autocheckmobile.common.adapters

import androidx.recyclerview.widget.DiffUtil
import com.iniongungroup.mobile.droid.autocheckmobile.entities.BaseEntity

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

val BASE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<BaseEntity>() {

    override fun areItemsTheSame(oldItem: BaseEntity, newItem: BaseEntity): Boolean {
        return oldItem.uniqueKey == newItem.uniqueKey
    }

    override fun areContentsTheSame(oldItem: BaseEntity, newItem: BaseEntity): Boolean {
        return oldItem == newItem
    }

}