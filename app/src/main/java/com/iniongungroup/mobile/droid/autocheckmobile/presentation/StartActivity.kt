package com.iniongungroup.mobile.droid.autocheckmobile.presentation

import android.os.Bundle
import com.iniongungroup.mobile.droid.autocheckmobile.BR
import com.iniongungroup.mobile.droid.autocheckmobile.R
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseActivity
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.livedataevent.LiveDataEventObserver
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.navigation.AppActivityNavCommands
import com.iniongungroup.mobile.droid.autocheckmobile.databinding.StartActivityBinding
import javax.inject.Inject

class StartActivity : BaseActivity<StartActivityBinding, StartActivityViewModel>() {

    @Inject lateinit var startActivityViewModel: StartActivityViewModel
    private lateinit var binding: StartActivityBinding

    override fun getLayoutId() = R.layout.start_activity

    override fun getViewModel() = startActivityViewModel

    override fun getBindingVariable() = BR.viewModel

    override fun getBinding(binding: StartActivityBinding) { this.binding = binding }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusAndNavigationBar()
    }

    override fun setViewModelObservers() {
        super.setViewModelObservers()
        observeShowInventory()
    }

    private fun observeShowInventory() {
        startActivityViewModel.showInventory.observe(this, LiveDataEventObserver {
            startActivity(AppActivityNavCommands.getInventoryActivityIntent(this))
            finish()
        })
    }

}