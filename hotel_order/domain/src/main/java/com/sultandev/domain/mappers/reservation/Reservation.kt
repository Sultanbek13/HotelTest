package com.sultandev.domain.mappers.reservation

import com.sultandev.data.models.reservation.ReservationData

data class Reservation(
    val arrivalCountry: String,
    val departure: String,
    val fuelCharge: Int,
    val horating: Int,
    val hotelLocation: String,
    val hotelName: String,
    val id: Int,
    val numberOfNights: Int,
    val nutrition: String,
    val ratingName: String,
    val room: String,
    val serviceCharge: Int,
    val tourDateStart: String,
    val tourDateStop: String,
    val tourPrice: Int
)

fun ReservationData.toDomain() = Reservation(
    arrivalCountry, departure, fuelCharge, horating, hotelLocation, hotelName, id, numberOfNights, nutrition, ratingName, room, serviceCharge, tourDateStart, tourDateStop, tourPrice
)