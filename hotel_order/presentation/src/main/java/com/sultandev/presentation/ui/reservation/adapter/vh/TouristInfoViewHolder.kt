package com.sultandev.presentation.ui.reservation.adapter.vh

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.R
import com.sultandev.presentation.databinding.ItemVhInfoTuristBinding
import com.sultandev.presentation.ui.reservation.adapter.ReservationAdapter

internal class TouristInfoViewHolder(
    private val binding: ItemVhInfoTuristBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var checkStateHide: Boolean = false

    fun bind() {
        binding.apply {
            stateHide.setOnClickListener {
                if (checkStateHide) {
                    stateHide.setImageResource(R.drawable.ic_navigate_to_bottom)
                    moreInfo.isVisible = false
                    checkStateHide = false
                } else {
                    stateHide.setImageResource(R.drawable.ic_navigate_to_top)
                    moreInfo.isVisible = true
                    checkStateHide = true
                }
            }
        }
    }
}