package com.project.aluveryx.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import java.math.BigDecimal

data class Product(
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val description: String? = null
)
