package com.project.aluveryx.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluveryx.sampleData.sampleSections
import com.project.aluveryx.ui.screens.HomeScreen
import com.project.aluveryx.ui.theme.AluveryXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           App(onFabClick = {
               startActivity(Intent(this, ProductFormActivity::class.java ))
           })
        }
    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {}
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
            HomeScreen(sampleSections, Modifier.padding(innerPadding))
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun AppPreview() {
    App()
}

