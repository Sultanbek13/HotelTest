package com.sultandev.presentation.ui.reservation

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.sultandev.base.extensions.collectFlow
import com.sultandev.domain.mappers.reservation.Reservation
import com.sultandev.presentation.R
import com.sultandev.presentation.base.BaseHotelOrderFragment
import com.sultandev.presentation.base.Resource
import com.sultandev.presentation.databinding.FragmentReservationBinding
import com.sultandev.presentation.di.injectors.HotelOrderComponentProvider
import com.sultandev.presentation.ui.extension.click
import com.sultandev.presentation.ui.reservation.adapter.ReservationAdapter
import javax.inject.Inject

class ReservationFragment :
    BaseHotelOrderFragment<ReservationViewModel, FragmentReservationBinding>(
        FragmentReservationBinding::inflate
    ) {

    @Inject
    override lateinit var factory: ReservationViewModel.Factory

    private val viewModel: ReservationViewModel by assistedViewModel()

    private val adapter: ReservationAdapter by lazy {
        ReservationAdapter(
            listenerToPay = object : ReservationAdapter.ListenerToPay {
                override fun toPay() {
                    findNavController().navigate(R.id.action_reservationFragment_to_paidFragment)
                }
            }
        )
    }

    override fun injectInAttachToFragment() {
        (requireActivity().applicationContext as HotelOrderComponentProvider).inject(this)
    }

    override fun getDataInOnCreate() {
        viewModel.getReservationInfo()
    }

    override fun FragmentReservationBinding.navigation() {
        iconToBack.click {
            findNavController().popBackStack()
        }
    }

    override fun FragmentReservationBinding.setupViews() {
        rvReservation.adapter = adapter
    }

    override fun FragmentReservationBinding.observeViewModel() {
        collectFlow(viewModel.reservation) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        setDataToList(reservation)
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

    override fun FragmentReservationBinding.loading(state: Boolean) {
        loading.isVisible = state
        rvReservation.isVisible = !state
    }

    private fun setDataToList(reservation: Reservation) {
        val listScreen = arrayListOf<ReservationAdapter.Types>()
        reservation.apply {
            listScreen.add(
                ReservationAdapter.Types.HotelInfo(
                    hotelName = hotelName,
                    hotelLocation = hotelLocation,
                    hotelRating = horating,
                    ratingName = ratingName
                )
            )
            listScreen.add(
                ReservationAdapter.Types.ReservationInfo(
                    departure = departure,
                    arrivalCountry = arrivalCountry,
                    tourDateStart = tourDateStart,
                    tourDateStop = tourDateStop,
                    numberOfNights = numberOfNights,
                    hotelName = hotelName,
                    room = room,
                    nutrition = nutrition
                )
            )
            listScreen.add(
                ReservationAdapter.Types.CustomerInfo
            )
            listScreen.add(
                ReservationAdapter.Types.TouristInfo
            )

            listScreen.add(
                ReservationAdapter.Types.TouristAdd
            )

            listScreen.add(
                ReservationAdapter.Types.ResultInfo(
                    tourPrice = tourPrice,
                    fuelCharge = fuelCharge,
                    serviceCharge = serviceCharge
                )
            )

            listScreen.add(
                ReservationAdapter.Types.ButtonToPay(
                    toPayPrice = (tourPrice + fuelCharge + serviceCharge)
                )
            )
        }
        adapter.submitList(listScreen)
    }
}
