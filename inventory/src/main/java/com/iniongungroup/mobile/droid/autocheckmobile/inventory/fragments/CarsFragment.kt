package com.iniongungroup.mobile.droid.autocheckmobile.inventory.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iniongungroup.mobile.droid.autocheckmobile.common.adapters.BaseListAdapter
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseFragment
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.livedataevent.LiveDataEventObserver
import com.iniongungroup.mobile.droid.autocheckmobile.entities.Car
import com.iniongungroup.mobile.droid.autocheckmobile.entities.Make
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.BR
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.InventoryActivity
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.InventoryViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.R
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.databinding.CarListItemBinding
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.databinding.CarsFragmentBinding
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.databinding.MakeListItemBinding

class CarsFragment : BaseFragment<CarsFragmentBinding, InventoryViewModel>() {

    private val inventoryViewModel by lazy { (requireActivity() as InventoryActivity).inventoryViewModel }

    private lateinit var binding: CarsFragmentBinding

    override fun getViewModel() = inventoryViewModel

    override fun getLayoutId() = R.layout.cars_fragment

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutBinding(binding: CarsFragmentBinding) { this.binding = binding }

    private val makesAdapter by lazy {
        object : BaseListAdapter<MakeListItemBinding, Make>(inventoryViewModel, R.layout.make_list_item) {

            override fun getEntityBindingVariable() = BR.make

            override fun getViewModelBindingVariable() = BR.viewModel
        }
    }

    private val carsAdapter by lazy {
        object : BaseListAdapter<CarListItemBinding, Car>(inventoryViewModel, R.layout.car_list_item) {

            override fun getEntityBindingVariable() = BR.car

            override fun getViewModelBindingVariable() = BR.viewModel
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCarsAndMakesRecyclerViews()
    }

    private fun setupCarsAndMakesRecyclerViews() {
        with(binding) {
            makesRecyclerView.adapter = makesAdapter

            carsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {}
                      //  inventoryViewModel.getMoreCars()
                }

            })
            carsRecyclerView.adapter = carsAdapter
        }
    }

    override fun setViewModelObservers() {
        super.setViewModelObservers()
        observeCarsAndMakes()
        observeShowCarDetails()
        observeCars()
    }

    private fun observeCarsAndMakes() {
        inventoryViewModel.carsAndMakes.observe(this, {
            makesAdapter.submitList(it.second)
            carsAdapter.submitList(it.first)
        })
    }

    private fun observeCars() {
        inventoryViewModel.cars.observe(this, {
            carsAdapter.submitList(it)
        })
    }

    private fun observeShowCarDetails() {
        inventoryViewModel.showCarDetails.observe(this, LiveDataEventObserver {
            if (it)
                navigateTo(CarsFragmentDirections.actionCarsFragmentToCarDetailsFragment())
        })
    }

    override fun showLoading() { binding.progressBar.visibility = View.VISIBLE }

    override fun hideLoading() { binding.progressBar.visibility = View.GONE }
}