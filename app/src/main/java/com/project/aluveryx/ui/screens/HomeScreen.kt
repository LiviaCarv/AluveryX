package com.project.aluveryx.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.Product
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
        LazyColumn(Modifier) {
            items(sections.toList()) { (title, productList) ->
                ProductsSection(title, productList)
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