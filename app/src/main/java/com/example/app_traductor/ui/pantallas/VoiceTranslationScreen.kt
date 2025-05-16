package com.example.app_traductor.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_traductor.ui.componentes.BackButton
import com.example.app_traductor.ui.componentes.OptionButton
import com.example.app_traductor.ui.componentes.TopBarOnApp

@Composable
fun VoiceTranslationScreen(
    onClickBack: () -> Unit
) {
    TopBarOnApp(title = "FISI TRADUCTOR") { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFE6F9FF))
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "TRADUCCIÓN POR VOZ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(100.dp))

            OptionButton("TRADUCCIÓN\nEN TIEMPO REAL", modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(100.dp))

            OptionButton(
                "CONVERSACIÓN\nDOBLE",
                modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.weight(1f))

            BackButton(
                onClickBack = onClickBack
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VoiceTranslationScreenPreview() {
    VoiceTranslationScreen(onClickBack = {})
}

