package com.sultandev.presentation.ui.reservation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.sultandev.presentation.ui.reservation.adapter.ReservationAdapter

internal class ReservationAdapterDiffUtil :
    DiffUtil.ItemCallback<ReservationAdapter.Types>() {
    override fun areItemsTheSame(
        oldItem: ReservationAdapter.Types,
        newItem: ReservationAdapter.Types
    ): Boolean {
        return when {
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: ReservationAdapter.Types,
        newItem: ReservationAdapter.Types
    ): Boolean {
        return when {
            else -> false
        }
    }
}