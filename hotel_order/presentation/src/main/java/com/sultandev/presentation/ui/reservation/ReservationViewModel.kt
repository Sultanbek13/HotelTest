package com.sultandev.presentation.ui.reservation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultandev.base.view_model.ViewModelFactory
import com.sultandev.domain.mappers.reservation.Reservation
import com.sultandev.domain.mappers.room.Rooms
import com.sultandev.domain.usecases.reservation.GetReservationInfoUseCase
import com.sultandev.presentation.base.Resource
import com.sultandev.util.base_result.Status
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ReservationViewModel @AssistedInject constructor(
    private val getReservation: GetReservationInfoUseCase,
    @Assisted savedStateHandle: SavedStateHandle
): ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelFactory<ReservationViewModel>

    private val _reservation = MutableStateFlow<Resource<Reservation>>(Resource.Loading)
    internal val reservation = _reservation.asSharedFlow()

    fun getReservationInfo() = viewModelScope.launch {
        getReservation.invoke().also {
            when(it.status) {
                Status.SUCCESS -> it.data?.let { reservation ->
                    _reservation.emit(Resource.Success(reservation))
                }

                Status.ERROR -> it.errorThrowable?.let { error ->
                    _reservation.emit(Resource.Error(error))
                }
            }
        }
    }
}