package com.iniongungroup.mobile.droid.autocheckmobile.inventory

import android.os.Bundle
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseActivity
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.databinding.InventoryActivityBinding
import javax.inject.Inject

class InventoryActivity: BaseActivity<InventoryActivityBinding, InventoryViewModel>() {

    @Inject
    lateinit var inventoryViewModel: InventoryViewModel
    private lateinit var binding: InventoryActivityBinding

    override fun getLayoutId() = R.layout.inventory_activity

    override fun getViewModel() = inventoryViewModel

    override fun getBindingVariable() = BR.viewModel

    override fun getBinding(binding: InventoryActivityBinding) { this.binding = binding }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusAndNavigationBar()
    }
}