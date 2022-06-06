package io.linkey.app.android.clean_architecture_sample_compose.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.linkey.app.android.domain.entity.PagingResource
import io.linkey.app.android.domain.entity.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object ViewModelUtil {

    fun <T> ViewModel.callUseCase(
        invoke : Flow<Resource<T>>,
        doSomething : (result : UiState<T>) -> Unit,
    ) {
        invoke.onEach {
            doSomething(
                when (it) {
                    is Resource.Success -> UiState(data = it.data!!)
                    is Resource.Error -> UiState(error = true)
                    is Resource.Loading -> UiState(isLoading = true)
                }
            )
        }.launchIn(viewModelScope)
    }

    fun <T> ViewModel.callPagingUseCase(
        invoke : Flow<PagingResource<List<T>>>,
        page : Int,
        state : LiveData<PagingUiState<T>>,
        _this : MutableLiveData<PagingUiState<T>>
    ) {

        val lastCheck = state.value?.let(PagingUiState<T>::last) ?: false
        val loadingCheck = state.value?.let(PagingUiState<T>::isLoading) ?: false
        if (page != 0 && (lastCheck || loadingCheck)) return

        invoke.onEach {
            when (it) {
                is PagingResource.Success -> state.setPagingStateData(_this, page, it)
                is PagingResource.Error -> state.setBottomLoading(false, _this, true)
                is PagingResource.Loading -> state.setBottomLoading(true, _this)
            }
        }.launchIn(viewModelScope)
    }

    private fun <T> LiveData<PagingUiState<T>>.setBottomLoading(
        isLoading : Boolean,
        _this : MutableLiveData<PagingUiState<T>>,
        error : Boolean = false
    ) {
        val result = this.value?.let { currentState ->
            currentState.copy(isLoading = isLoading, error = error)
        } ?: PagingUiState<T>(isLoading = isLoading, error = error)
        _this.postValue(result)
    }

    private fun <T> LiveData<PagingUiState<T>>.setPagingStateData(
        _this : MutableLiveData<PagingUiState<T>>,
        page : Int,
        resource: PagingResource<List<T>>
    ) {
        val data = if (page != 0) {
            this.getPagingStateData()
        } else {
            listOf()
        }.toMutableList()
        data.addAll(resource.data!!)

        _this.value = this.value?.let {
            it.copy(
                data = data,
                totalElement = resource.totalElements,
                last = resource.last,
                page = resource.pageNumber,
                isLoading = false,
                refresh = false,
            )
        } ?: run {
            PagingUiState(
                data = resource.data,
                totalElement = resource.totalElements,
                last = resource.last,
                page = resource.pageNumber,
                isLoading = false,
                refresh = false,
            )
        }
    }

    private fun <T> LiveData<PagingUiState<T>>.getPagingStateData() = this.value?.let {
        it.data?.let { data -> data } ?: listOf()
    } ?: listOf()
}