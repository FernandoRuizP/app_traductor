package com.example.app_traductor.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
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
fun MainScreen(userName: String = "Luis") {
    TopBarOnApp {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F9FF))
                .padding(it) // padding de la TopBar
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            GreetingSection(userName)
            Spacer(modifier = Modifier.height(100.dp))
            OptionButton("TRADUCCIÓN\nPOR VOZ", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(100.dp))
            OptionButton("TRADUCCIÓN\nPOR TEXTO", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.weight(1f))
            BackButton(onClickBack = { /* Acción de regreso */ })
        }
    }
}

@Composable
fun GreetingSection(userName: String) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hola, $userName",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "Elige una opción",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Usuario",
            modifier = Modifier.size(64.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable

fun MainScreenPreview() {
    MainScreen()
}



