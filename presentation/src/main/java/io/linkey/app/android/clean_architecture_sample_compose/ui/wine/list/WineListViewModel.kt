package io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.BaseViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.UiState
import io.linkey.app.android.domain.entity.wine.Wine
import io.linkey.app.android.domain.entity.wine.WineCategory
import io.linkey.app.android.domain.usecase.wine.GetWinesUseCase
import javax.inject.Inject

@HiltViewModel
class WineListViewModel @Inject constructor(
    private val getSamplesUseCase: GetWinesUseCase
) : BaseViewModel(){

    private val _categoryState = mutableStateOf(UiState(WineCategory.sample))
    val categoryState : State<UiState<List<WineCategory>>> = _categoryState

    private val _wineListState = mutableStateOf(UiState<List<Wine>>())
    val wineListState : State<UiState<List<Wine>>> = _wineListState

    init {
        selectCategory()
    }

    private fun getWines(wine: String) {
        callUseCase(getSamplesUseCase.invoke(wine)) {
            _wineListState.value = UiState(data = it)
        }
    }

    fun selectCategory(wine : String = "reds") {
        _wineListState.value = wineListState.value.copy(data = null)
        _categoryState.value = categoryState.value.copy(
            data = WineCategory.sample.map {
                if (it.title == wine) {
                    it.copy(isChecked = true)
                } else {
                    it.copy(isChecked = false)
                }
            }
        )
        getWines(wine)
    }

    fun currentSelectCategory() = categoryState.value.data!!.first(WineCategory::isChecked).title
}