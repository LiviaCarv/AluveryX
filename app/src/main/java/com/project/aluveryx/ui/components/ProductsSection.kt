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
import com.project.aluveryx.Product
import com.project.aluveryx.R
import java.math.BigDecimal

@Composable
fun ProductsSection(
    productsList: List<Product>,
    modifier: Modifier = Modifier,
    sectionTitle: String = "Title"
) {
    Column (modifier = modifier) {
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
    ProductsSection(listTest)
}

val listTest = listOf(
    Product("Produto 1", BigDecimal(19.99), R.drawable.pizza),
    Product("Produto 2", BigDecimal(20.5), R.drawable.burger),
    Product("Produto 3", BigDecimal(66.99), R.drawable.cupcake),
    Product("Produto 4", BigDecimal(74)),
    Product("Produto 5", BigDecimal(1.25)),
)