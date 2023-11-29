package com.sultandev.presentation.ui.reservation.adapter.vh

import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.R
import com.sultandev.presentation.databinding.ItemVhInfoReservationBinding
import com.sultandev.presentation.ui.reservation.adapter.ReservationAdapter

internal class ReservationInfoViewHolder(
    private val binding: ItemVhInfoReservationBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ReservationAdapter.Types.ReservationInfo) {
        binding.apply {
            data.apply {
                tvValueDeparture.text = departure
                tvValueCountryOrCity.text = arrivalCountry
                tvValueDate.text = "${tourDateStart} - ${tourDateStop}"
                tvCountNight.text = "${numberOfNights} ${root.resources.getString(R.string.tv_days)}"
                tvValueHotel.text = hotelName
                tvValueRoomNumber.text = room
                tvValueNutrition.text = nutrition
            }
        }
    }
}