package com.example.app_traductor.ui.pantallas

import android.app.Activity
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_traductor.ui.componentes.BackButton
import com.example.app_traductor.ui.componentes.TopBarStarter
import com.example.app_traductor.ui.validaciones.validateConfirmPassword
import com.example.app_traductor.ui.validaciones.validateEmail
import com.example.app_traductor.ui.validaciones.validateName
import com.example.app_traductor.ui.validaciones.validatePassword
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth

@Composable
fun RegisterScreen(
    onClickBack: () -> Unit = {},
    onClickLogin: () -> Unit = {},
    onSuccessfulRegister: () -> Unit  = {}
) {
    TopBarStarter(title = "FISI TRADUCTOR") { innerPadding ->
        RegisterBodyContent(
            modifier = Modifier.padding(innerPadding),
            onClickBack = onClickBack,
            onClickLogin = onClickLogin,
            onSuccessfulRegister = onSuccessfulRegister
        )
    }
}

@Composable
fun RegisterBodyContent(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickLogin: () -> Unit,
    onSuccessfulRegister: () -> Unit
) {
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPass = remember { mutableStateOf("") }

    //Firebase
    val auth = Firebase.auth
    val activity = LocalView.current.context as Activity

    //Estados
    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordConfirmationError by remember { mutableStateOf("") }

    var registerError by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FCFF))
            .padding(8.dp),

    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("REGISTRARSE", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Nombre de usuario:", fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            placeholder = { Text("Introducir nombre de usuario") },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (nameError.isNotEmpty()) {
                    Text(
                        text = nameError,
                        color = Color.Red
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Email:", fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            placeholder = { Text("Introducir Email") },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (emailError.isNotEmpty()) {
                    Text(
                        text = emailError,
                        color = Color.Red
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Contraseña:", fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = { Text("**********") },
            shape = RoundedCornerShape(50),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (passwordError.isNotEmpty()) {
                    Text(
                        text = passwordError,
                        color = Color.Red
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text("Confirma tu contraseña:", fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = confirmPass.value,
            onValueChange = { confirmPass.value = it },
            placeholder = { Text("**********") },
            shape = RoundedCornerShape(50),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (passwordConfirmationError.isNotEmpty()) {
                    Text(
                        text = passwordConfirmationError,
                        color = Color.Red
                    )
                }
            }
        )
        if (registerError.isNotEmpty()) {
            Text(registerError, color = Color.Red)
        }
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(

            onClick = {
                val isValidName = validateName(username.value).first
                val isValidEmail = validateEmail(email.value).first
                val isValidPassword = validatePassword(password.value).first
                val isValidConfirmPassword = validateConfirmPassword(password.value, confirmPass.value).first

                nameError = validateName(username.value).second
                emailError = validateEmail(email.value).second
                passwordError = validatePassword(password.value).second
                passwordConfirmationError = validateConfirmPassword(password.value, confirmPass.value).second

                if (isValidName && isValidEmail && isValidPassword && isValidConfirmPassword) {
                    auth.createUserWithEmailAndPassword(email.value, password.value).
                    addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful){
                            onSuccessfulRegister()
                        }else{
                            registerError = when(task.isSuccessful){
                                is FirebaseAuthInvalidCredentialsException -> "Correo invalido"
                                is FirebaseAuthUserCollisionException -> "Correo ya registrado"
                                else -> "Error al registrarse"

                            }
                        }
                    }

                } else {
                    registerError = "Hubo un error en el register"
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(modifier = Modifier.width(80.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Divider(modifier = Modifier.width(80.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onClickLogin()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB6FF4E)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Inicia Sesión", color = Color.Red, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))


        BackButton(
            onClickBack = onClickBack,
        )

    }

    
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}