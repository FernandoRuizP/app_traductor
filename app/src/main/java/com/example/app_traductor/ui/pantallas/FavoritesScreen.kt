package com.example.app_traductor.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_traductor.ui.componentes.TopBarOnApp

@Composable
fun FavoritesScreen(
    onBack: () -> Unit,
    onHome: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val favoriteItems = listOf(
        "Here much observed",
        "Où sont les toilettes?",
        "Quanto custa?",
        "Can you repeat that, please?"
    )

    TopBarOnApp(title = "FISI TRADUCTOR") { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE6F9FF))
                .padding(16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("FAVORITOS ")
                    withStyle(style = SpanStyle(color = Color.Yellow)) {
                        append("⭐")
                    }
                },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            favoriteItems.forEach { item ->
                FavoriteItem(text = item, onClick = { onItemClick(item) })
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFF3399FF), CircleShape)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }

                IconButton(
                    onClick = onHome,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFF3399FF), CircleShape)
                ) {
                    Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Black)
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(text: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        color = Color.White,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = text)
            Icon(Icons.Default.ArrowForward, contentDescription = "Ver")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(
        onBack = {},
        onHome = {},
        onItemClick = { item -> println("Favorito: $item") }
    )
}
