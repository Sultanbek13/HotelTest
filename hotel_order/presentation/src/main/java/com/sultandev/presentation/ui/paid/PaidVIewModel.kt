package com.sultandev.presentation.ui.paid

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sultandev.base.view_model.ViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PaidVIewModel @AssistedInject constructor(@Assisted savedStateHandle: SavedStateHandle): ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelFactory<PaidVIewModel>
}