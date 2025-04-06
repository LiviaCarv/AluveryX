package com.project.aluveryx.ui.states

data class ProductFormScreenUiState(
    val url: String = "",
    val productName: String = "",
    val price: String = "",
    val description: String = "",
    val onUrlChange: (String) -> Unit = {},
    val onProductNameChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
)