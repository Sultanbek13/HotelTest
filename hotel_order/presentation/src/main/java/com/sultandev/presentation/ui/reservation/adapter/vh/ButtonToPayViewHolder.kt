package com.sultandev.presentation.ui.reservation.adapter.vh

import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.R
import com.sultandev.presentation.databinding.ItemVhBtnToPayBinding
import com.sultandev.presentation.ui.extension.toFormatAmount
import com.sultandev.presentation.ui.reservation.adapter.ReservationAdapter

internal class ButtonToPayViewHolder(
    private val binding: ItemVhBtnToPayBinding,
    private val listenerToPay: ReservationAdapter.ListenerToPay
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ReservationAdapter.Types.ButtonToPay) {
        binding.apply {
            btnToPay.text =
                "${root.resources.getString(R.string.tv_pay)} ${data.toPayPrice.toFormatAmount()}"
            btnToPay.setOnClickListener {
                listenerToPay.toPay()
            }
        }
    }
}