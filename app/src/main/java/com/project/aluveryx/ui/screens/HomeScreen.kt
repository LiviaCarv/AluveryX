package com.project.aluveryx.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.ui.components.CardProductItem
import com.project.aluveryx.model.Product
import com.project.aluveryx.sampleData.sampleSections
import com.project.aluveryx.ui.components.ProductsSection
import com.project.aluveryx.ui.components.SearchTextField
import com.project.aluveryx.ui.theme.AluveryXTheme

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {

    fun isShowSection(): Boolean {
        return searchText.isBlank()
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column(modifier = modifier) {
        val sections = state.sections
        val text = state.searchText
        val searchedProducts = state.searchedProducts
        SearchTextField(text, { newValue -> state.onSearchChange(newValue) })
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.isShowSection()) {
                items(sections.toList()) { (title, productList) ->
                    ProductsSection(title, productList)
                }
            } else {
                items(searchedProducts) { product ->
                    CardProductItem(
                        product = product,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryXTheme {
        HomeScreen(state = HomeScreenUiState(sections = sampleSections))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchPreview() {
    AluveryXTheme {
        HomeScreen(state = HomeScreenUiState(searchText = "sor", sections = sampleSections))
    }
}