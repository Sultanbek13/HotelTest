package com.sultandev.presentation.ui.paid

import androidx.navigation.fragment.findNavController
import com.sultandev.presentation.R
import com.sultandev.presentation.base.BaseHotelOrderFragment
import com.sultandev.presentation.databinding.FragmentPaidBinding
import com.sultandev.presentation.di.injectors.HotelOrderComponentProvider
import com.sultandev.presentation.ui.extension.click
import javax.inject.Inject

class PaidFragment: BaseHotelOrderFragment<PaidVIewModel, FragmentPaidBinding>(FragmentPaidBinding::inflate) {

    @Inject
    override lateinit var factory: PaidVIewModel.Factory

    override fun injectInAttachToFragment() {
        (requireActivity().applicationContext as HotelOrderComponentProvider).inject(this)
    }

    override fun getDataInOnCreate() {}

    override fun FragmentPaidBinding.loading(state: Boolean) {}

    override fun FragmentPaidBinding.navigation() {
        btnGood.click {
            findNavController().navigate(R.id.action_paidFragment_to_hotelFragment)
        }
        iconToBack.click {
            findNavController().popBackStack()
        }
    }

    override fun FragmentPaidBinding.setupViews() {}

    override fun FragmentPaidBinding.observeViewModel() {}
}