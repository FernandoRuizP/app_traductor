package com.example.app_traductor.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun LanguageSelectorRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        LanguageDropdown("English")
        LanguageDropdown("Spanish")
    }
}

@Composable
fun LanguageDropdown(selected: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selected) }

    Box {
        TextButton(onClick = { expanded = true }) {
            Text(text = selectedText)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOf("English", "Spanish", "French", "German").forEach { lang ->
                DropdownMenuItem(
                    text = { Text(lang) },
                    onClick = {
                        selectedText = lang
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TranslatedTextBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun CircleIconButton(icon: ImageVector, size: Dp, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(size)
            .background(Color(0xFF3399FF), shape = CircleShape)
    ) {
        Icon(icon, contentDescription = null, tint = Color.Black, modifier = Modifier.size(size * 0.5f))
    }
}

@Composable
fun BottomControlButtons(
    onPlay: () -> Unit,
    onRefresh: () -> Unit,
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        CircleIconButton(Icons.Default.PlayArrow, 50.dp, onClick = onPlay)
        CircleIconButton(Icons.Default.Refresh, 50.dp, onClick = onRefresh)
        CircleIconButton(Icons.Default.ArrowBack, 50.dp, onClick = onBack)
        CircleIconButton(Icons.Default.Home, 50.dp, onClick = onHome)
    }
}
