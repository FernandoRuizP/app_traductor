package com.example.app_traductor.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MicNone
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_traductor.ui.componentes.BottomControlButtons
import com.example.app_traductor.ui.componentes.CircleIconButton
import com.example.app_traductor.ui.componentes.LanguageSelectorRow
import com.example.app_traductor.ui.componentes.TopBarOnApp
import com.example.app_traductor.ui.componentes.TranslatedTextBox

@Composable
fun ConversationModeScreen(
    onBack: () -> Unit,
    onHome: () -> Unit,
    onStartListening: () -> Unit,
    onPlayAudio: () -> Unit,
    onRefresh: () -> Unit
) {
    TopBarOnApp(title = "FISI TRADUCTOR") { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE6F9FF))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "TRADUCCIÓN POR VOZ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LanguageSelectorRow()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Audio traducido en tiempo real",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            TranslatedTextBox("Bienvenido a FISI TRADUCTOR...")

            Spacer(modifier = Modifier.height(24.dp))

            CircleIconButton(
                icon = Icons.Filled.MicNone,
                size = 100.dp,
                onClick = onStartListening
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomControlButtons(
                onPlay = onPlayAudio,
                onRefresh = onRefresh,
                onBack = onBack,
                onHome = onHome
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConversationModeScreenPreview() {
    ConversationModeScreen(onBack = {},
        onHome = {},
        onStartListening = {},
        onPlayAudio = {},
        onRefresh = {})
}


