
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_traductor.ui.componentes.BackButton
import com.example.app_traductor.ui.componentes.TopBarStarter

@Composable
fun ForgotPasswordScreen(onClickBack: () -> Unit = {}) {
    TopBarStarter { innerPadding ->
        ForgotPasswordBodyContent(
            Modifier.padding(innerPadding),
            onClickBack = onClickBack
        )
    }
}

@Composable
fun ForgotPasswordBodyContent(modifier: Modifier = Modifier, onClickBack: () -> Unit = {}) {
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FCFF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("¿Olvidó su contraseña?", fontSize = 26.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Le enviaremos un código para que pueda\nrestablecer su contraseña.",
            color = Color.Red,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de correo electrónico
        InputField(label = "Email:", value = email.value, onValueChange = { email.value = it }, placeholder = "Introduzca su correo")

        Spacer(modifier = Modifier.height(16.dp))

        // Indicador visual
        DividerIndicator()

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de número de celular
        InputField(label = "Número de celular:", value = phone.value, onValueChange = { phone.value = it }, placeholder = "Introduzca su número de celular")

        Spacer(modifier = Modifier.height(32.dp))

        // Botón siguiente
        ActionButton(text = "> Siguiente", onClick = { /* Acción siguiente */ })

        Spacer(modifier = Modifier.height(32.dp))

        // Botón atrás
        BackButton(
            onClickBack = onClickBack
        )
    }
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Text(label, fontWeight = FontWeight.SemiBold)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(50),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DividerIndicator() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Divider(modifier = Modifier.width(80.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Box {
            Text(text = "o")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Divider(modifier = Modifier.width(80.dp))
    }
}


@Composable
fun ActionButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}
