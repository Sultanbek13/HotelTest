package com.sultandev.presentation.ui.peculiarity_adapter

import androidx.recyclerview.widget.DiffUtil

class PeculiarityDiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}