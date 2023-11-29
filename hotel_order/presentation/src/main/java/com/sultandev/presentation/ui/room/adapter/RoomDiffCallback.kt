package com.sultandev.presentation.ui.room.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sultandev.domain.mappers.room.Room

class RoomDiffCallback : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.name == newItem.name && oldItem.price == newItem.price && oldItem.imageUrls == newItem.imageUrls && oldItem.peculiarities == newItem.peculiarities
    }
}