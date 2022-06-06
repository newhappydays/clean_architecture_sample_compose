package io.linkey.app.android.clean_architecture_sample_compose.ui.base

data class UiState<T>(
    val data : T? = null,
    val isLoading : Boolean = false,
    val error : Boolean = false
)
