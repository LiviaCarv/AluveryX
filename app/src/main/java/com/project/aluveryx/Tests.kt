package com.project.aluveryx

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Product(modifier: Modifier = Modifier) {
    Surface(shape = RoundedCornerShape(15.dp), shadowElevation = 4.dp, modifier = modifier) {
        Row(
            Modifier
                .width(200.dp)
                .heightIn(min = 250.dp, max = 300.dp)
        ) {
            val imageSize = 100.dp
            Box (
                Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.tertiary
                            )
                        )
                    )
                    .fillMaxWidth()
                    .height(imageSize)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Product Item",
                    modifier = Modifier
                        .size(imageSize)
                        .offset(y = imageSize/2)
                        .clip(shape = CircleShape)
//                        .align(Alignment.TopCenter)
                    ,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(imageSize / 2))
            Column(Modifier.padding(16.dp)) {
                Text(
                    text ="product name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "R$ 14,33",
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)

                )
            }
        }
    }


}

@Preview
@Composable
private fun Product() {
    Product()
}