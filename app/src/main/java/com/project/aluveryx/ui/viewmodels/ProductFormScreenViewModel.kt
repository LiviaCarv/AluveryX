package com.project.aluveryx.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.project.aluveryx.ui.states.ProductFormScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductFormScreenViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<ProductFormScreenUiState> =
        MutableStateFlow(ProductFormScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {

        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = { url ->
                    _uiState.value = _uiState.value.copy(
                        url = url
                    )
                },
                onProductNameChange = {
                    _uiState.value = _uiState.value.copy(
                        productName = it
                    )
                },
                onPriceChange = { input ->
                    val formattedInput = input.replace(",", ".").filter {
                        it.isDigit() || it == '.'
                    }
                    if (formattedInput.count { it == '.' } <= 1) {
                        _uiState.value = _uiState.value.copy(
                            price = formattedInput
                        )
                    }
                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                }
            )
        }
    }
}



