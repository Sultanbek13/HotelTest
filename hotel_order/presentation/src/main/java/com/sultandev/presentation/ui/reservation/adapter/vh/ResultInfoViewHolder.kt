package com.sultandev.presentation.ui.reservation.adapter.vh

import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.databinding.ItemVhInfoResultBinding
import com.sultandev.presentation.ui.extension.toFormatAmount
import com.sultandev.presentation.ui.reservation.adapter.ReservationAdapter

internal class ResultInfoViewHolder(val binding: ItemVhInfoResultBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ReservationAdapter.Types.ResultInfo) {
        var resultToPay: Int
        binding.apply {
            data.apply {
                resultToPay = tourPrice + fuelCharge + serviceCharge
                tvValueTour.text = tourPrice.toFormatAmount()
                tvValueFuelSurcharge.text = fuelCharge.toFormatAmount()
                tvValueServiceFee.text = serviceCharge.toFormatAmount()
                tvCountToPay.text = resultToPay.toFormatAmount()
            }
        }
    }
}