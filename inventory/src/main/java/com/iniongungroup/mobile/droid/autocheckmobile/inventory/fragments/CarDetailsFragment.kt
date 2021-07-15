package com.iniongungroup.mobile.droid.autocheckmobile.inventory.fragments

import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseFragment
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.BR
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.InventoryActivity
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.InventoryViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.R
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.databinding.CarDetailsFragmentBinding

class CarDetailsFragment : BaseFragment<CarDetailsFragmentBinding, InventoryViewModel>() {

    private val inventoryViewModel by lazy { (requireActivity() as InventoryActivity).inventoryViewModel }

    private lateinit var binding: CarDetailsFragmentBinding

    override fun getViewModel() = inventoryViewModel

    override fun getLayoutId() = R.layout.car_details_fragment

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutBinding(binding: CarDetailsFragmentBinding) { this.binding = binding }

    override fun setViewModelObservers() {
        super.setViewModelObservers()
    }
}