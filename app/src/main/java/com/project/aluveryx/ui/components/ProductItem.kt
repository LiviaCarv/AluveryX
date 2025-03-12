package com.project.aluveryx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.aluveryx.model.Product
import com.project.aluveryx.R
import com.project.aluveryx.extensions.toBrazilianCurrency
import com.project.aluveryx.ui.theme.AluveryXTheme
import com.project.aluveryx.ui.theme.Green4
import java.math.BigDecimal

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Surface(shape = RoundedCornerShape(15.dp), shadowElevation = 4.dp, modifier = modifier) {
        Column(
            Modifier
                .width(200.dp)
                .heightIn(min = 250.dp, max = 300.dp)
                .background(
                    MaterialTheme.colorScheme.onPrimary
                )
        ) {
            val imageSize = 100.dp
            Box(
                Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        )
                    )
                    .fillMaxWidth()
                    .height(imageSize)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "Product Item",
                    modifier = Modifier
                        .size(imageSize)
                        .offset(y = imageSize/2)
                        .clip(shape = CircleShape)
                        .align(Alignment.TopCenter),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(imageSize / 2))
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price.toBrazilianCurrency(),
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(550),
                    color = Green4

                )
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    AluveryXTheme {
        ProductItem(Product("Produto", BigDecimal(19.99)))
    }
}
