package com.project.aluveryx.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.ui.theme.AluveryXTheme

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

}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    AluveryXTheme {
        ProductFormScreen()
    }
}