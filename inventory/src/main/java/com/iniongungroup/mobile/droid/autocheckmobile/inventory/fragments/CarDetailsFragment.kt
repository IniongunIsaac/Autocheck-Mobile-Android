package com.iniongungroup.mobile.droid.autocheckmobile.inventory.fragments

import android.os.Bundle
import com.iniongungroup.mobile.droid.autocheckmobile.common.adapters.BaseListAdapter
import com.iniongungroup.mobile.droid.autocheckmobile.common.base.BaseFragment
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.formatted
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.livedataevent.LiveDataEventObserver
import com.iniongungroup.mobile.droid.autocheckmobile.entities.CarMedia
import com.iniongungroup.mobile.droid.autocheckmobile.entities.currencyFormatted
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.BR
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.InventoryActivity
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.InventoryViewModel
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.R
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.databinding.CarDetailsFragmentBinding
import com.iniongungroup.mobile.droid.autocheckmobile.inventory.databinding.CarMediaListItemBinding

class CarDetailsFragment : BaseFragment<CarDetailsFragmentBinding, InventoryViewModel>() {

    private val inventoryViewModel by lazy { (requireActivity() as InventoryActivity).inventoryViewModel }

    private lateinit var binding: CarDetailsFragmentBinding

    override fun getViewModel() = inventoryViewModel

    override fun getLayoutId() = R.layout.car_details_fragment

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutBinding(binding: CarDetailsFragmentBinding) { this.binding = binding }

    private val carMediaAdapter by lazy {
        object : BaseListAdapter<CarMediaListItemBinding, CarMedia>(inventoryViewModel, R.layout.car_media_list_item) {

            override fun getEntityBindingVariable() = BR.media

            override fun getViewModelBindingVariable() = BR.viewModel
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickListeners()
        setupMediaRecyclerView()
        showCarDetails()
    }

    private fun setupMediaRecyclerView() {
        binding.mediaRecyclerView.adapter = carMediaAdapter
        carMediaAdapter.submitList(inventoryViewModel.carDetailsAndMedia.second)
    }

    private fun setOnClickListeners() {
        with(binding) {
            backImageView.setOnClickListener { navigateBack() }
        }
    }

    private fun showCarDetails() {
        with(inventoryViewModel.carDetailsAndMedia.first) {
            with(binding){
                carImageView.setImageURI(imageUrl)
                carNameTextView.text = model.make.name
                sellingPriceTextView.text = price.currencyFormatted()
                marketPriceTextView.text = marketplacePrice.currencyFormatted()
                installmentTextView.text = installment.currencyFormatted()
                yearTextView.text = "$year"
                ratingTextView.text = gradeScore.formatted()
                mileageTextView.text = "$mileage$mileageUnit"
                wheelTypeTextView.text = model.wheelType.capitalize()
                locationTextView.text = "$address, $city, $state, $country"
                transmissionTextView.text = transmission.capitalize()
                interiorColorTextView.text = interiorColor
                exteriorColorTextView.text = exteriorColor
                engineTypeTextView.text = engineType.capitalize()
                conditionTextView.text = sellingCondition.capitalize()
                hasWarrantyTextView.text = if (hasWarranty) "Yes" else "No"
                hasFinancingTextView.text = if (hasFinancing) "Yes" else "No"
                isInsuredTextView.text = if (insured) "Yes" else "No"
            }
        }
    }

    override fun setViewModelObservers() {
        super.setViewModelObservers()
        observeCarMediaURL()
    }

    private fun observeCarMediaURL() {
        inventoryViewModel.carMediaURL.observe(this, LiveDataEventObserver {
            binding.carImageView.setImageURI(it)
        })
    }
}