package com.project.aluveryx.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.aluveryx.dao.ProductDao
import com.project.aluveryx.model.Product
import com.project.aluveryx.sampleData.sampleCandies
import com.project.aluveryx.sampleData.sampleDrinks
import com.project.aluveryx.sampleData.sampleProducts
import com.project.aluveryx.ui.states.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {
    private val dao = ProductDao()

    var uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState(
        sections = mapOf(
            "All products" to dao.products(),
            "On Sale" to sampleDrinks + sampleCandies,
            "Candies" to sampleCandies,
            "Drinks" to sampleDrinks),
        onSearchChange = { text ->
            uiState = uiState.copy(
                searchText = text,
                searchedProducts = productsFilter(text)
            )

        }
    ))
        private set

    private fun containsInProductName(text: String) = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true,
        )
    }

    private fun productsFilter(text: String) : List<Product> {
        return if (text.isNotBlank()) {
            sampleProducts.filter(containsInProductName(text)) + dao.products().filter(containsInProductName(text))
        } else emptyList()
    }

}