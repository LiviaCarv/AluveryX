package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.aluveryx.model.Product
import com.project.aluveryx.R
import com.project.aluveryx.extensions.toBrazilianCurrency
import com.project.aluveryx.sampleData.sampleProducts
import com.project.aluveryx.ui.theme.AluveryXTheme
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
) {
    Card(
        modifier = modifier.fillMaxWidth().heightIn(150.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
             product.description?.let {
                 Text(
                     text = product.description,
                     modifier = Modifier.padding(16.dp),
                     maxLines = 4,
                     overflow = TextOverflow.Ellipsis
                 )
             }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardProductItemPreview() {
    AluveryXTheme {
        Surface {
            CardProductItem(
                product = sampleProducts.random(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardProductItemWithDescriptionPreview() {
    AluveryXTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "Test",
                    price = BigDecimal("9.99"),
                    description = LoremIpsum(50).values.first()
                ),
            )
        }
    }
}