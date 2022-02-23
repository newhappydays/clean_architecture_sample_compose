package io.linkey.app.android.clean_architecture_sample_compose.ui.base

data class PagingUiState<T>(
    val data : List<T>? = null,
    val totalElement : Int = 0,
    val last : Boolean = false,
    val page : Int = 0,
    val isLoading : Boolean = false,
    val error : String = "",
)
