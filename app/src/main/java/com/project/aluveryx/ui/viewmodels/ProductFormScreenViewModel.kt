package com.project.aluveryx.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.project.aluveryx.dao.ProductDao
import com.project.aluveryx.model.Product
import com.project.aluveryx.ui.states.ProductFormScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

class ProductFormScreenViewModel : ViewModel() {
    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<ProductFormScreenUiState> =
        MutableStateFlow(ProductFormScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {

        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = { url ->
                    _uiState.value = _uiState.value.copy(
                        url = url,
                        isShowPreview = url.isNotBlank()
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

    fun save() {
        val product =  _uiState.value.run {
            createProduct(
                this.productName,
                this.price,
                this.url,
                this.description
            )

        }
        dao.saveProduct(product)
    }

    private fun createProduct(
        name: String,
        price: String,
        url: String,
        description: String
    ): Product {
        val convertedPrice = try {
            BigDecimal(price)
        } catch (exception: NumberFormatException) {
            BigDecimal.ZERO
        }
        return Product(name, convertedPrice, url, description)
    }

}



