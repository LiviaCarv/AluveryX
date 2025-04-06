package com.project.aluveryx.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.aluveryx.dao.ProductDao
import com.project.aluveryx.model.Product
import com.project.aluveryx.sampleData.sampleCandies
import com.project.aluveryx.sampleData.sampleDrinks
import com.project.aluveryx.sampleData.sampleProducts
import com.project.aluveryx.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())
    val uiState get() = _uiState.asStateFlow()


    init {

        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { text ->
                    _uiState.value = _uiState.value.copy(
                        searchText = text,
                        searchedProducts = productsFilter(text)
                    )

                }
            )

        }
        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "All products" to products,
                        "On Sale" to sampleDrinks + sampleCandies,
                        "Candies" to sampleCandies,
                        "Drinks" to sampleDrinks
                    ),
                    searchedProducts = productsFilter(_uiState.value.searchText)
                )

            }
        }
    }

    private fun containsInProductName(text: String) = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true,
        )
    }

    private fun productsFilter(text: String): List<Product> {
        return if (text.isNotBlank()) {
            sampleProducts.filter(containsInProductName(text)) + dao.products().value
                .filter(containsInProductName(text))
        } else emptyList()
    }

}