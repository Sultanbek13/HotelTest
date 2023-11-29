package com.sultandev.presentation.ui.room

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultandev.base.view_model.ViewModelFactory
import com.sultandev.domain.mappers.room.Rooms
import com.sultandev.domain.usecases.room.GetRoomsUseCase
import com.sultandev.presentation.base.Resource
import com.sultandev.util.base_result.Status
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RoomViewModel @AssistedInject constructor(
    private val getRooms: GetRoomsUseCase,
    @Assisted savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private val _rooms = MutableStateFlow<Resource<Rooms>>(Resource.Loading)
    internal val rooms = _rooms.asSharedFlow()

    @AssistedFactory
    interface Factory : ViewModelFactory<RoomViewModel>

    fun getRooms() = viewModelScope.launch {
        getRooms.invoke().also {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { hotels ->
                    _rooms.emit(Resource.Success(hotels))
                }

                Status.ERROR -> it.errorThrowable?.let { error ->
                    _rooms.emit(Resource.Error(error))
                }
            }
        }
    }
}