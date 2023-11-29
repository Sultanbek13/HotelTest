package com.sultandev.presentation.ui.hotel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultandev.base.view_model.ViewModelFactory
import com.sultandev.domain.mappers.hotel.Hotel
import com.sultandev.domain.usecases.hotel.GetHotelsUseCase
import com.sultandev.presentation.base.Resource
import com.sultandev.util.base_result.Status
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class HotelViewModel @AssistedInject constructor(
    private val getHotels: GetHotelsUseCase,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _hotels = MutableStateFlow<Resource<Hotel>>(Resource.Loading)
    internal val hotels = _hotels.asSharedFlow()

    @AssistedFactory
    interface Factory : ViewModelFactory<HotelViewModel>

    fun getHotels() = viewModelScope.launch {
        getHotels.invoke().also {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { hotels ->
                    _hotels.emit(Resource.Success(hotels))
                }

                Status.ERROR -> it.errorThrowable?.let { error ->
                    _hotels.emit(Resource.Error(error))
                }
            }
        }
    }
}