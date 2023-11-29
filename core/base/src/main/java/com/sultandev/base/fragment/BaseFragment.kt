package com.sultandev.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sultandev.base.view_model.AssistedArgsViewModelFactory
import com.sultandev.base.view_model.ViewModelFactory

typealias Inflate<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    var binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding?.root
    }

    abstract val factory: ViewModelFactory<out VM>

    fun <VM> assistedViewModel(
    ): Lazy<VM> =
        viewModels(ownerProducer = { this }, factoryProducer = {
            AssistedArgsViewModelFactory(factory, this, arguments ?: Bundle.EMPTY)
        })

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}