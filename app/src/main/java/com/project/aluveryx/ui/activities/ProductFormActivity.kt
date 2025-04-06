package com.project.aluveryx.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.dao.ProductDao
import com.project.aluveryx.ui.screens.ProductFormScreen
import com.project.aluveryx.ui.theme.AluveryXTheme
import com.project.aluveryx.ui.viewmodels.ProductFormScreenViewModel

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AluveryXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel by viewModels<ProductFormScreenViewModel>()

                    ProductFormScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSaveClick = {
                            dao.saveProduct(it)
                            finish()
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    AluveryXTheme {
//        ProductFormScreen({})
    }
}