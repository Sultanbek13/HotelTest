package com.sultandev.presentation.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sultandev.presentation.databinding.ItemVhAddTouristBinding
import com.sultandev.presentation.databinding.ItemVhBtnToPayBinding
import com.sultandev.presentation.databinding.ItemVhInfoAboutCustomerBinding
import com.sultandev.presentation.databinding.ItemVhInfoHotelBinding
import com.sultandev.presentation.databinding.ItemVhInfoReservationBinding
import com.sultandev.presentation.databinding.ItemVhInfoResultBinding
import com.sultandev.presentation.databinding.ItemVhInfoTuristBinding
import com.sultandev.presentation.ui.reservation.adapter.diffutil.ReservationAdapterDiffUtil
import com.sultandev.presentation.ui.reservation.adapter.vh.ButtonToPayViewHolder
import com.sultandev.presentation.ui.reservation.adapter.vh.CustomerInfoViewHolder
import com.sultandev.presentation.ui.reservation.adapter.vh.HotelInfoViewHolder
import com.sultandev.presentation.ui.reservation.adapter.vh.ReservationInfoViewHolder
import com.sultandev.presentation.ui.reservation.adapter.vh.ResultInfoViewHolder
import com.sultandev.presentation.ui.reservation.adapter.vh.TouristAddViewHolder
import com.sultandev.presentation.ui.reservation.adapter.vh.TouristInfoViewHolder
import java.lang.IllegalArgumentException
import java.lang.reflect.Type

internal class ReservationAdapter(
    private val listenerToPay: ListenerToPay
) : ListAdapter<ReservationAdapter.Types, RecyclerView.ViewHolder>(
    ReservationAdapterDiffUtil()
) {

    interface ListenerToPay {
        fun toPay()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ReservationTypes.HOTEL_INFO_TYPE.value -> HotelInfoViewHolder(
                ItemVhInfoHotelBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            ReservationTypes.RESERVATION_INFO.value -> ReservationInfoViewHolder(
                ItemVhInfoReservationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            ReservationTypes.CUSTOMER_INFO.value -> CustomerInfoViewHolder(
                ItemVhInfoAboutCustomerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            ReservationTypes.TOURIST_INFO.value -> TouristInfoViewHolder(
                ItemVhInfoTuristBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            ReservationTypes.TOURIST_ADD.value -> TouristAddViewHolder(
                ItemVhAddTouristBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )


            ReservationTypes.RESULT_INFO.value -> ResultInfoViewHolder(
                ItemVhInfoResultBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )


            ReservationTypes.BUTTON_TO_PAY.value -> ButtonToPayViewHolder(
                ItemVhBtnToPayBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), listenerToPay = listenerToPay
            )

            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is Types.HotelInfo -> ReservationTypes.HOTEL_INFO_TYPE.value
            is Types.ReservationInfo -> ReservationTypes.RESERVATION_INFO.value
            is Types.CustomerInfo -> ReservationTypes.CUSTOMER_INFO.value
            is Types.TouristInfo -> ReservationTypes.TOURIST_INFO.value
            is Types.TouristAdd -> ReservationTypes.TOURIST_ADD.value
            is Types.ResultInfo -> ReservationTypes.RESULT_INFO.value
            is Types.ButtonToPay -> ReservationTypes.BUTTON_TO_PAY.value
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HotelInfoViewHolder -> holder.bind(
                currentList[position] as Types.HotelInfo
            )

            is ReservationInfoViewHolder -> holder.bind(
                currentList[position] as Types.ReservationInfo
            )

            is CustomerInfoViewHolder -> holder.bind()

            is TouristInfoViewHolder -> holder.bind()

            is TouristAddViewHolder -> holder.bind()

            is ResultInfoViewHolder -> holder.bind(
                currentList[position] as Types.ResultInfo
            )

            is ButtonToPayViewHolder -> holder.bind(
                currentList[position] as Types.ButtonToPay
            )
        }
    }

    internal sealed class Types {
        data class HotelInfo(
            val hotelName: String,
            val hotelLocation: String,
            val hotelRating: Int,
            val ratingName: String,
        ) : Types()

        data class ReservationInfo(
            val departure: String,
            val arrivalCountry: String,
            val tourDateStart: String,
            val tourDateStop: String,
            val numberOfNights: Int,
            val hotelName: String,
            val room: String,
            val nutrition: String,
        ) : Types()

        object CustomerInfo : Types()

        object TouristInfo : Types()

        object TouristAdd : Types()

        data class ResultInfo(
            val tourPrice: Int,
            val fuelCharge: Int,
            val serviceCharge: Int,
        ) : Types()

        data class ButtonToPay(
            val toPayPrice: Int,
        ) : Types()

    }

    internal enum class ReservationTypes(var value: Int) {
        HOTEL_INFO_TYPE(1),
        RESERVATION_INFO(2),
        CUSTOMER_INFO(3),
        TOURIST_INFO(4),
        TOURIST_ADD(5),
        RESULT_INFO(6),
        BUTTON_TO_PAY(7)
    }
}