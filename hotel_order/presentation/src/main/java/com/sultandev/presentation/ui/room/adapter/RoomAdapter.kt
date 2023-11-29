package com.sultandev.presentation.ui.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.sultandev.domain.mappers.room.Room
import com.sultandev.presentation.databinding.ItemRoomBinding
import com.sultandev.presentation.ui.peculiarity_adapter.PeculiarityAdapter
import com.sultandev.presentation.ui.extension.toFormatAmount

class RoomAdapter : ListAdapter<Room, RoomAdapter.ViewHolder>(RoomDiffCallback()) {

    private var onClickSelectRoom: (() -> Unit)? = null

    fun setOnClickSelectRoom(invoke: () -> Unit) {
        onClickSelectRoom = invoke
    }

    inner class ViewHolder(val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        val images = arrayListOf<SlideModel>()
        currentItem.imageUrls.onEach {
            images.add(SlideModel(it))
        }
        val peculiarityAdapter = PeculiarityAdapter()

        with(holder.binding) {
            titleRoom.text = currentItem.name
            priceCount.text = currentItem.price.toFormatAmount()
            detailPrice.text = currentItem.pricePer
            rvPeculiarity.adapter = peculiarityAdapter
            roomImageSlider.setImageList(images)
            btnSelectHotelNumber.setOnClickListener {
                onClickSelectRoom?.invoke()
            }
        }
        peculiarityAdapter.submitList(currentItem.peculiarities)
    }
}