package io.linkey.app.android.clean_architecture_sample_compose.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.linkey.app.android.domain.entity.PagingResource
import io.linkey.app.android.domain.entity.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class BaseViewModel : ViewModel() {

    private val _errorMessage = mutableStateOf<String>("")
    val errorMessage : State<String> = _errorMessage

    fun httpErrorMessage() {
        _errorMessage.value = "네트워크 상태가 불안정합니다. 잠시 후 다시 시도해주세요."
    }

    fun <T> State<PagingUiState<T>>.callPagingUseCase(
        invoke : Flow<PagingResource<List<T>>>,
        _this : MutableState<PagingUiState<T>>
    ) {
        val lastCheck = this.value.last
        val loadingCheck = this.value.isLoading
        if (lastCheck || loadingCheck) return

        invoke.onEach {
            when (it) {
                is PagingResource.Success -> setPagingStateData(_this, it)
                is PagingResource.Error -> {
                    httpErrorMessage()
                    setBottomLoading(false, _this)
                }
                is PagingResource.Loading -> setBottomLoading(true, _this)
            }
        }.launchIn(viewModelScope)
    }

    private fun <T> State<PagingUiState<T>>.setBottomLoading(
        isLoading : Boolean,
        _this : MutableState<PagingUiState<T>>
    ) {
        val result = this.value.copy(isLoading = isLoading)
        _this.value = result
    }

    private fun <T> State<PagingUiState<T>>.setPagingStateData(
        _this : MutableState<PagingUiState<T>>,
        resource: PagingResource<List<T>>
    ) {
        val data = this.getPagingStateData().toMutableList()
        data.addAll(resource.data!!)

        _this.value = this.value.copy(
            data = data,
            totalElement = resource.totalElements,
            last = resource.last,
            page = resource.pageNumber,
            isLoading = false,
        )
    }

    fun <T> State<PagingUiState<T>>.getPagingStateData() = this.value.data ?: listOf()

    fun <T> callUseCase(
        invoke : Flow<Resource<T>>,
        doSomething : (result : T) -> Unit
    ) {
        invoke.onEach {
            when (it) {
                is Resource.Success -> doSomething(it.data!!)
                is Resource.Error -> httpErrorMessage()
                is Resource.Loading -> { }
            }
        }.launchIn(viewModelScope)
    }

}