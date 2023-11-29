package com.sultandev.presentation.ui.reservation.adapter.vh

import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.databinding.ItemVhInfoHotelBinding
import com.sultandev.presentation.ui.reservation.adapter.ReservationAdapter

internal class HotelInfoViewHolder(
    private val binding: ItemVhInfoHotelBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ReservationAdapter.Types.HotelInfo) {
        binding.apply {
            ratingHotel.text = "${data.hotelRating} ${data.ratingName}"
            nameHotel.text = data.hotelName
            locationHotel.text = data.hotelLocation
        }
    }
}