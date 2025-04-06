package com.project.aluveryx.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.sampleData.sampleSections
import com.project.aluveryx.ui.screens.HomeScreen
import com.project.aluveryx.ui.states.HomeScreenUiState
import com.project.aluveryx.ui.theme.AluveryXTheme
import com.project.aluveryx.ui.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }) {
                val viewModel by viewModels<HomeScreenViewModel>()

                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {}, content: @Composable () -> Unit = {}
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
    App(content = { HomeScreen(state = HomeScreenUiState(sections = sampleSections)) })
}

