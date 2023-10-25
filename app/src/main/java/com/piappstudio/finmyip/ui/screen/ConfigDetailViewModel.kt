package com.piappstudio.finmyip.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.finmyip.network.IPDetail
import com.piappstudio.finmyip.network.PiRepository
import com.piappstudio.finmyip.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ConfigDetailScreenState(val isProgress:Boolean = true, val ipDetail: IPDetail? = null)

@HiltViewModel
class ConfigDetailViewModel @Inject constructor(val repository: PiRepository): ViewModel() {

    private val _uiState = MutableStateFlow(ConfigDetailScreenState())
    val uiState = _uiState.asStateFlow()


    fun fetchIpConfig() {
        viewModelScope.launch {
            repository.fetchIpConfig().collect {result->
                when (result.status) {
                    Resource.Status.LOADING-> {
                        _uiState.update { it.copy(isProgress = true) }
                    }
                    Resource.Status.SUCCESS -> {
                        _uiState.update { it.copy(isProgress = false, ipDetail = result.data) }
                    } else -> {
                        _uiState.update { it.copy(isProgress = false) }
                    }
                }
            }
        }

    }

}