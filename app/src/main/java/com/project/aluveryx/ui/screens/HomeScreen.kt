package com.project.aluveryx.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.ui.components.CardProductItem
import com.project.aluveryx.model.Product
import com.project.aluveryx.sampleData.sampleProducts
import com.project.aluveryx.sampleData.sampleSections
import com.project.aluveryx.ui.components.ProductsSection
import com.project.aluveryx.ui.components.SearchTextField
import com.project.aluveryx.ui.theme.AluveryXTheme

class HomeScreenUiState(search: String = "") {
    var text by mutableStateOf(search)
        private set

    val productsFilter
        get() =
            sampleProducts.filter {
                (it.name.contains(
                    text,
                    ignoreCase = true
                ) || it.description?.contains(
                    text,
                    ignoreCase = true,
                ) ?: false)
            }

    val onSearchChange: (String) -> Unit = { search ->
        text = search
    }

    fun isShowSection(): Boolean {
        return text.isBlank()
    }
}

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    modifier: Modifier = Modifier,
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column(modifier = modifier) {
        val text = state.text
        val searchedProducts = remember(text) { state.productsFilter }
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
        HomeScreen(sampleSections)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchPreview() {
    AluveryXTheme {
        HomeScreen(sampleSections, state = HomeScreenUiState("sor"))
    }
}