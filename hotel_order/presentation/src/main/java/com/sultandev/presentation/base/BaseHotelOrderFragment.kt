package com.sultandev.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sultandev.base.fragment.BaseFragment
import com.sultandev.base.fragment.Inflate

abstract class BaseHotelOrderFragment<VM: ViewModel, VB: ViewBinding>(
    inflate: Inflate<VB>,
): BaseFragment<VM, VB>(
    inflate
) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectInAttachToFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataInOnCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            setupViews()
            observeViewModel()
            navigation()
        }
    }

    abstract fun injectInAttachToFragment()

    abstract fun getDataInOnCreate()

    abstract fun VB.observeViewModel()

    abstract fun VB.setupViews()

    abstract fun VB.navigation()

    abstract fun VB.loading(state: Boolean)

}