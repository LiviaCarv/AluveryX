package com.project.aluveryx.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.aluveryx.model.Product
import com.project.aluveryx.sampleData.sampleProducts

@Composable
fun ProductsSection(
    sectionTitle: String,
    productsList: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = sectionTitle,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(productsList) { product ->
                ProductItem(product)

            }

        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
private fun ProductsSectionPreview() {
    ProductsSection("Promoções", sampleProducts)
}

