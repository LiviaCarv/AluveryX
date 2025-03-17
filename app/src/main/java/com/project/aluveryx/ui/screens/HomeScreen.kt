package com.project.aluveryx.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.ui.components.CardProductItem
import com.project.aluveryx.model.Product
import com.project.aluveryx.sampleData.sampleDrinks
import com.project.aluveryx.sampleData.sampleSections
import com.project.aluveryx.ui.components.ProductsSection
import com.project.aluveryx.ui.components.SearchTextField
import com.project.aluveryx.ui.theme.AluveryXTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SearchTextField()
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            items(sections.toList()) { (title, productList) ->
//                ProductsSection(title, productList)
//            }
            items(sampleDrinks) { product ->
                CardProductItem(product = product, modifier = Modifier.padding(horizontal = 16.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryXTheme {
        HomeScreen(sampleSections)
    }
}