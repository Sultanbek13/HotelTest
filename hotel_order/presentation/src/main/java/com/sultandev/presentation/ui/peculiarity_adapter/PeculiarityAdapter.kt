package com.sultandev.presentation.ui.peculiarity_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.databinding.ItemPeculiarityBinding

class PeculiarityAdapter : ListAdapter<String, PeculiarityAdapter.ViewHolder>(PeculiarityDiffCallback()) {
    inner class ViewHolder(val binding: ItemPeculiarityBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPeculiarityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        with(holder.binding) {
            tvPeculiarity.text = currentItem
        }
    }
}