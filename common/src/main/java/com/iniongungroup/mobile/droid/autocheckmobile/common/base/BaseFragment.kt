package com.iniongungroup.mobile.droid.autocheckmobile.common.base

import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.livedataevent.LiveDataEventObserver
import com.iniongungroup.mobile.droid.autocheckmobile.common.utils.state.AppState
import dagger.android.support.DaggerFragment

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

abstract class BaseFragment<in D : ViewDataBinding, out V : BaseViewModel> : DaggerFragment() {

    abstract fun getViewModel(): V

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getLayoutBinding(binding: D)

    open fun setViewModelObservers() {}

    private val baseActivity by lazy {
        activity as BaseActivity<*, *>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: D = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        binding.apply {

            setVariable(getBindingVariable(), getViewModel())

            executePendingBindings()

            lifecycleOwner = this@BaseFragment
        }

        setViewModelObservers()

        setNotificationObserver()

        getLayoutBinding(binding)

        return binding.root
    }

    open fun setNotificationObserver() {

        getViewModel().notificationLiveData.observe(viewLifecycleOwner, LiveDataEventObserver {

            when (it.state) {

                AppState.FAILED -> {
                    hideLoading()
                    it.message?.let { message ->
                        showMessage(requireView(), message, isError = true)
                    }
                }

                AppState.WARNING -> {
                    hideLoading()
                    it.message?.let { message ->
                        showMessage(requireView(), message, isWarning = true)
                    }

                }

                AppState.LOADING -> {
                    showLoading()
                }

                AppState.SUCCESS -> {
                    hideLoading()
                    it.message?.let { message ->
                        showMessage(requireView(), message)
                    }

                }

            }

        })

    }

    protected fun navigateTo(navDirection: NavDirections) {
        findNavController().navigate(navDirection)
    }

    protected fun navigateBack() {
        findNavController().navigateUp()
    }

    fun hideKeyBoard(token: IBinder) {
        val inputMethodManager = activity?.getSystemService<InputMethodManager>()
        inputMethodManager?.hideSoftInputFromWindow(token, 0)
    }

    fun showKeyBoard() {
        val inputMethodManager = activity?.getSystemService<InputMethodManager>()
        inputMethodManager?.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    fun showMessage(
        view: View,
        message: String,
        isError: Boolean = false,
        duration: Int = Snackbar.LENGTH_SHORT,
        isWarning: Boolean = false
    ) = (activity as BaseActivity<*, *>).showMessage(view, message, isError, duration, isWarning)



    open fun showLoading() = (activity as BaseActivity<*, *>).showLoading()

    open fun hideLoading() = (activity as BaseActivity<*, *>).hideLoading()

    fun showView(view: View, shouldShow: Boolean) {
        activity?.let {
            (it as BaseActivity<*, *>).showView(view, shouldShow)
        }
    }

}