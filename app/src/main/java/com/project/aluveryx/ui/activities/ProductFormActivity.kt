package com.project.aluveryx.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.aluveryx.R
import com.project.aluveryx.model.Product
import com.project.aluveryx.ui.theme.AluveryXTheme
import java.math.BigDecimal

const val TAG = "ProductFormScreen"

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AluveryXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductFormScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen(modifier: Modifier = Modifier) {
    var url by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier)
        Text(
            text = "Create a new product",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 28.sp,
        )
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
            onValueChange = { url = it },
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
            onValueChange = { name = it },
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
            onValueChange = { input ->
                val formattedInput = input.replace(",",".").filter {
                    it.isDigit() || it == '.'
                }
                if (formattedInput.count {it == '.'} <= 1) {
                    price = formattedInput
                }
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
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth().heightIn(100.dp),
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
            onClick = { saveProduct(name, price, url, description) }
        ) {
            Text("Save")
        }
    }

}

fun saveProduct(name: String, price: String, url: String, description: String) {
    val convertedPrice = try {
        BigDecimal(price)
    } catch (exception: NumberFormatException) {
        BigDecimal.ZERO
    }
    val newProduct = Product(name, convertedPrice, url, description)
    Log.i(TAG, "New product created: $newProduct")

}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    AluveryXTheme {
        ProductFormScreen()
    }
}