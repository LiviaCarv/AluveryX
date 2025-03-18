package com.project.aluveryx.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.aluveryx.extensions.toBrazilianCurrency
import com.project.aluveryx.ui.theme.AluveryXTheme
import java.math.BigDecimal

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
    var price by rememberSaveable { mutableStateOf(BigDecimal(0)) }
    var description by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create a new product",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 28.sp,
        )
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            modifier = Modifier.fillMaxWidth(),
           label = {
               Text("Image's url")
           }
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Product name")
            }
        )
        OutlinedTextField(
            value = price.toBrazilianCurrency(),
            onValueChange = { price = it.toBigDecimal() },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Price")
            }
            )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth().heightIn(100.dp),
            label = {
                Text("Description")
            }
        )
        ElevatedButton( modifier = Modifier.fillMaxWidth(), onClick = {}) {
            Text("Save")
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    AluveryXTheme {
        ProductFormScreen()
    }
}