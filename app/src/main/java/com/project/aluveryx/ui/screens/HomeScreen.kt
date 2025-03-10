package com.project.aluveryx.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.sampleData.sampleProducts
import com.project.aluveryx.ui.components.ProductsSection
import com.project.aluveryx.ui.theme.AluveryXTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        ProductsSection(
            sampleProducts
        )
        ProductsSection(
            sampleProducts
        )

    }
}

@Preview(showBackground = true)
@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryXTheme {
        HomeScreen()
    }
}