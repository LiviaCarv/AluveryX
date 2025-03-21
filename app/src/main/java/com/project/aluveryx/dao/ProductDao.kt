package com.project.aluveryx.dao

import androidx.compose.runtime.mutableStateListOf
import com.project.aluveryx.model.Product

class ProductDao {
    companion object {
        private val products = mutableStateListOf<Product>()

    }

    fun products() = products.toList()

    fun saveProduct(product: Product) {
        products.add(index = 0, element = product)
    }

}