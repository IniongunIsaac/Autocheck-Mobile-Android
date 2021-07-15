package com.iniongungroup.mobile.droid.autocheckmobile.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.entities.BaseEntity

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

abstract class BaseListAdapter<VDB: ViewDataBinding, ENT: BaseEntity>(private val viewModel: BaseViewModel, @LayoutRes private val layoutId: Int)
    : ListAdapter<BaseEntity, RecyclerView.ViewHolder>(BASE_DIFF_CALLBACK) {

    abstract fun getEntityBindingVariable(): Int

    abstract fun getViewModelBindingVariable(): Int

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<ENT>).bind(viewModel, getItem(position) as ENT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: VDB = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        return ListBaseViewHolder(binding)
    }

    inner class ListBaseViewHolder (private val binding: VDB) : BaseViewHolder<ENT>(binding) {

        override fun bind(viewModel: BaseViewModel, entity: ENT) {
            binding.apply {
                setVariable(getEntityBindingVariable(), entity)
                setVariable(getViewModelBindingVariable(), viewModel)
                executePendingBindings()
            }
        }
    }
}