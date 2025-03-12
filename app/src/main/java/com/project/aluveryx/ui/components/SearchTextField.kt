package com.project.aluveryx.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.aluveryx.ui.theme.AluveryXTheme

@Composable
fun SearchTextField(modifier: Modifier = Modifier) {
    var value by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            value = newValue
            Log.i("HomeScreen", "new value = $newValue")
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(50),
        label = {
            Text("Product")
        },
        placeholder = {
            Text("What you're looking for?")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SearchTextFieldPreview() {
    AluveryXTheme {
        SearchTextField()
    }
}