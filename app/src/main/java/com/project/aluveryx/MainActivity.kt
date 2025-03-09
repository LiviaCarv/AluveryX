package com.project.aluveryx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.ui.theme.AluveryXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           App()
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    AluveryXTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier.padding(innerPadding)) {
                ProductsSection(
                    listTest
                )
                ProductsSection(
                    listTest
                )

            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun AppPreview() {
    App()
}

