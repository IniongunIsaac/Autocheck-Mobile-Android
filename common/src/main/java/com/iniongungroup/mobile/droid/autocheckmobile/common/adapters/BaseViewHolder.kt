package com.iniongungroup.mobile.droid.autocheckmobile.common.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.entities.BaseEntity

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

abstract class BaseViewHolder<ENT: BaseEntity>(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(viewModel: BaseViewModel, entity: ENT)
}