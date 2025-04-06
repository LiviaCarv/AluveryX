package com.project.aluveryx.dao

import com.project.aluveryx.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {
    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())

    }

    fun products() = products.asStateFlow()

    fun saveProduct(product: Product) {
        products.value += product
    }

}