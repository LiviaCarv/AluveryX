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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.project.aluveryx.ui.states.ProductFormScreenUiState
import com.project.aluveryx.ui.viewmodels.ProductFormScreenViewModel
import java.math.BigDecimal

// stateful
@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit,
    viewModel: ProductFormScreenViewModel,
    modifier: Modifier = Modifier
) {

    val state by viewModel.uiState.collectAsState()

    fun onSaveClick() {
        onSaveClick(createProduct(state.productName, state.price, state.url, state.description))
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

private fun createProduct(name: String, price: String, url: String, description: String): Product {
    val convertedPrice = try {
        BigDecimal(price)
    } catch (exception: NumberFormatException) {
        BigDecimal.ZERO
    }
    return Product(name, convertedPrice, url, description)
}

