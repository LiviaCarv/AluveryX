package com.project.aluveryx

import java.math.BigDecimal

data class Product(
    val name: String,
    val price: BigDecimal,
    val image: Int = R.drawable.placeholder
)
