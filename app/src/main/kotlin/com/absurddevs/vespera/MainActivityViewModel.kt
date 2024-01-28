package com.absurddevs.vespera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.absurddevs.vespera.MainActivityUiState.Loading
import com.absurddevs.vespera.MainActivityUiState.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

// TODO remove theses temporary classes
enum class DarkThemeConfig {
    FOLLOW_SYSTEM,
    LIGHT,
    DARK
}
data class CustomData(
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
)

@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {
    private val data: Flow<CustomData> = flow {
        emit(CustomData(DarkThemeConfig.FOLLOW_SYSTEM, true))
    }

    val uiState: StateFlow<MainActivityUiState> = data.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed interface MainActivityUiState {
    data object Loading: MainActivityUiState
    data class Success(val data: CustomData) : MainActivityUiState
}