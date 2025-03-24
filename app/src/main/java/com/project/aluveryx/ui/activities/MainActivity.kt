package com.project.aluveryx.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.dao.ProductDao
import com.project.aluveryx.sampleData.sampleCandies
import com.project.aluveryx.sampleData.sampleDrinks
import com.project.aluveryx.ui.screens.HomeScreen
import com.project.aluveryx.ui.screens.HomeScreenUiState
import com.project.aluveryx.ui.theme.AluveryXTheme

class MainActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }) {
                val sections = mapOf(
                    "All products" to dao.products(),
                    "On Sale" to sampleDrinks + sampleCandies,
                    "Candies" to sampleCandies,
                    "Drinks" to sampleDrinks,
                )
                val state = remember { HomeScreenUiState() }
                HomeScreen(sections = sections, state = state)
            }
        }
    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AluveryXTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(Icons.Default.Add, "Add new product")
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun AppPreview() {
    App()
}

