package com.sultandev.presentation.ui.hotel

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.models.SlideModel
import com.sultandev.base.extensions.collectFlow
import com.sultandev.domain.mappers.hotel.Hotel
import com.sultandev.presentation.R
import com.sultandev.presentation.base.BaseHotelOrderFragment
import com.sultandev.presentation.base.Resource
import com.sultandev.presentation.databinding.FragmentHotelBinding
import com.sultandev.presentation.di.injectors.HotelOrderComponentProvider
import com.sultandev.presentation.ui.peculiarity_adapter.PeculiarityAdapter
import com.sultandev.presentation.ui.extension.toFormatAmount
import javax.inject.Inject

class HotelFragment : BaseHotelOrderFragment<HotelViewModel, FragmentHotelBinding>(
    FragmentHotelBinding::inflate
) {

    @Inject
    override lateinit var factory: HotelViewModel.Factory

    private val viewModel: HotelViewModel by assistedViewModel()

    override fun getDataInOnCreate() {
        viewModel.getHotels()
    }

    private val peculiarityAdapter: PeculiarityAdapter by lazy(LazyThreadSafetyMode.NONE) { PeculiarityAdapter() }

    override fun injectInAttachToFragment() {
        (requireActivity().applicationContext as HotelOrderComponentProvider).inject(this)
    }

    override fun FragmentHotelBinding.navigation() {
        itemHotel.btnSelectHotelNumber.setOnClickListener {
            if (hotelName.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString(HOTEL_NAME_KEY, hotelName)
                findNavController().navigate(R.id.action_hotelFragment_to_numberFragment, bundle)
            }
        }
    }

    private var hotelName = ""

    override fun FragmentHotelBinding.setupViews() {
        itemHotel.rvPeculiarityHotel.adapter = peculiarityAdapter
    }

    override fun FragmentHotelBinding.observeViewModel() {
        collectFlow(viewModel.hotels) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { hotel ->
                        setData(hotel)
                    }
                    loading(false)
                }

                is Resource.Loading -> {
                    loading(true)
                }

                is Resource.Error -> {
                    loading(false)
                }
            }
        }
    }

    override fun FragmentHotelBinding.loading(state: Boolean) {
        loading.isVisible = state
        itemHotel.root.isVisible = !state
    }

    private fun setData(hotel: Hotel) {
        hotelName = hotel.name
        val images = arrayListOf<SlideModel>()
        hotel.imageUrls.onEach {
            images.add(SlideModel(it))
        }
        binding?.itemHotel?.apply {
            hotel.apply {
                nameHotel.text = name
                locationHotel.text = adress
                priceCount.text =
                    "${root.resources.getString(R.string.tv_from)} ${minimalPrice.toFormatAmount()}"
                ratingHotel.text = "${rating} ${ratingName}"
                detailPrice.text = priceForIt
                hotelDescription.text = aboutTheHotel.description

                hotelImageSlider.setImageList(images)
                peculiarityAdapter.submitList(aboutTheHotel.peculiarities)
            }
        }
    }

    companion object {
        const val HOTEL_NAME_KEY = "hotel_name"
    }
}