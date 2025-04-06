package com.project.aluveryx.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.project.aluveryx.dao.ProductDao
import com.project.aluveryx.model.Product
import com.project.aluveryx.sampleData.sampleCandies
import com.project.aluveryx.sampleData.sampleDrinks
import com.project.aluveryx.sampleData.sampleProducts
import com.project.aluveryx.ui.states.HomeScreenUiState
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val dao = ProductDao()

    var uiState: HomeScreenUiState by mutableStateOf(
        HomeScreenUiState(
            onSearchChange = { text ->
                uiState = uiState.copy(
                    searchText = text,
                    searchedProducts = productsFilter(text)
                )

            }
        ))
        private set

    init {
        viewModelScope.launch {
            dao.products().collect { products ->
                uiState = uiState.copy (
                    sections = mapOf(
                        "All products" to products,
                        "On Sale" to sampleDrinks + sampleCandies,
                        "Candies" to sampleCandies,
                        "Drinks" to sampleDrinks
                    ),
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