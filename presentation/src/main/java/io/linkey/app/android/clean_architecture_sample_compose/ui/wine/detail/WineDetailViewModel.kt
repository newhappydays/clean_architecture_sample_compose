package io.linkey.app.android.clean_architecture_sample_compose.ui.wine.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.BaseViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.UiState
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.ViewModelUtil.callUseCase
import io.linkey.app.android.domain.entity.wine.WineDetail
import io.linkey.app.android.domain.usecase.wine.GetOneWineUseCase
import javax.inject.Inject

@HiltViewModel
class WineDetailViewModel @Inject constructor(
    private val getOneWineUseCase: GetOneWineUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _wineDetailState = mutableStateOf(UiState<WineDetail>())
    val wineDetailState : State<UiState<WineDetail>> = _wineDetailState

    init {
        savedStateHandle.get<String>("wine")?.let { wine ->
            savedStateHandle.get<String>("wineId")?.let { wineId ->
                getWine(wine, wineId.toLong())
            }
        }
    }

    private fun getWine(
        wine : String,
        wineId : Long
    ) {
        callUseCase(getOneWineUseCase.invoke(wine, wineId)) { state ->
            state.data?.let {
                _wineDetailState.value = UiState(data = it)
            }
        }
    }

}