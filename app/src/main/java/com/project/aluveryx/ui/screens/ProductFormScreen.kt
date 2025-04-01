package com.project.aluveryx.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.aluveryx.R
import com.project.aluveryx.model.Product
import java.math.BigDecimal

class ProductFormScreenUiState(
    val url: String = "",
    val productName: String = "",
    val price: String = "",
    val description: String = "",
    val onUrlChange: (String) -> Unit = {},
    val onProductNameChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
)

// stateful
@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    var url by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    val state =
        ProductFormScreenUiState(
            url = url,
            productName = name,
            price = price,
            description = description,
            onProductNameChange = {
                name = it
            },
            onPriceChange = { input ->
                val formattedInput = input.replace(",", ".").filter {
                    it.isDigit() || it == '.'
                }
                if (formattedInput.count { it == '.' } <= 1) {
                    price = formattedInput
                }
            },
            onUrlChange = {
                url = it
            },
            onDescriptionChange = {
                description = it
            },
        )


    fun onSaveClick() {
        onSaveClick(createProduct(name, price, url, description))
    }

    ProductFormScreen(state = state, onSaveClick = { onSaveClick() }, modifier = modifier)
}


// stateless
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    onSaveClick: () -> Unit,
    state: ProductFormScreenUiState,
    modifier: Modifier = Modifier
) {
    val url = state.url
    val name = state.productName
    val price = state.price
    val description = state.description

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Product") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (url.isNotBlank()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "Product Item",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.placeholder)
                )
            }
            OutlinedTextField(
                value = url,
                onValueChange = { newUrl -> state.onUrlChange(newUrl) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Image's url")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Next
                ),
            )
            OutlinedTextField(
                value = name,
                onValueChange = { newName -> state.onProductNameChange(newName) },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Product name")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words
                )
            )
            OutlinedTextField(
                value = price,
                onValueChange = { newPrice ->
                    state.onPriceChange(newPrice)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Price")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Decimal
                )
            )
            OutlinedTextField(
                value = description,
                onValueChange = { newDescription -> state.onDescriptionChange(newDescription) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp),
                label = {
                    Text("Description")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                ),
            )
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSaveClick
            ) {
                Text("Save")
            }
        }

    }
}

fun createProduct(name: String, price: String, url: String, description: String): Product {
    val convertedPrice = try {
        BigDecimal(price)
    } catch (exception: NumberFormatException) {
        BigDecimal.ZERO
    }
    return Product(name, convertedPrice, url, description)
}
