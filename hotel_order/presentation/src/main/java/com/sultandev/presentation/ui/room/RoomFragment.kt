package com.sultandev.presentation.ui.room

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.sultandev.base.extensions.collectFlow
import com.sultandev.presentation.R
import com.sultandev.presentation.base.BaseHotelOrderFragment
import com.sultandev.presentation.base.Resource
import com.sultandev.presentation.databinding.FragmentRoomBinding
import com.sultandev.presentation.di.injectors.HotelOrderComponentProvider
import com.sultandev.presentation.ui.extension.click
import com.sultandev.presentation.ui.hotel.HotelFragment.Companion.HOTEL_NAME_KEY
import com.sultandev.presentation.ui.room.adapter.RoomAdapter
import javax.inject.Inject

class RoomFragment: BaseHotelOrderFragment<RoomViewModel, FragmentRoomBinding>(
    FragmentRoomBinding::inflate
) {

    private val hotelName: String?
        get() = arguments?.getString(HOTEL_NAME_KEY)

    @Inject
    override lateinit var factory: RoomViewModel.Factory

    private val viewModel: RoomViewModel by assistedViewModel()

    private val roomAdapter: RoomAdapter by lazy(LazyThreadSafetyMode.NONE) {
        RoomAdapter()
    }

    override fun injectInAttachToFragment() {
        (requireActivity().applicationContext as HotelOrderComponentProvider).inject(this)
    }

    override fun getDataInOnCreate() {
        viewModel.getRooms()
    }

    override fun FragmentRoomBinding.navigation() {
        roomAdapter.setOnClickSelectRoom {
            findNavController().navigate(R.id.action_numberFragment_to_reservationFragment)
        }
        iconToBack.click {
            findNavController().popBackStack()
        }
    }

    override fun FragmentRoomBinding.setupViews() {
        toolBarHotelName.text = hotelName
        rvRooms.adapter = roomAdapter
    }

    override fun FragmentRoomBinding.observeViewModel() {
        collectFlow(viewModel.rooms) {
            when(it) {
                is Resource.Success -> {
                    roomAdapter.submitList(it.data?.roomData)
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

    override fun FragmentRoomBinding.loading(state: Boolean) {
        loading.isVisible = state
        rvRooms.isVisible = !state
    }
}