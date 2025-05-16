package com.example.app_traductor.ui.pantallas

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_traductor.ui.componentes.TopBarStarter
import com.example.app_traductor.ui.validaciones.validateEmail
import com.example.app_traductor.ui.validaciones.validatePassword
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth


@Composable
fun LoginScreen(
    onBack: () -> Unit = {},
    onClickRegister: () -> Unit = {},
    onClickForgotPass: () -> Unit = {},
    onSucessLogin:() -> Unit =  {}

) {
    TopBarStarter { innerPadding ->
        LoginBodyContent(
            Modifier.padding(innerPadding),
            onBack = onBack,
            onClickRegister = onClickRegister,
            onClickForgotPass = onClickForgotPass,
            onSucessLogin = onSucessLogin
        )
    }
}

@Composable
fun LoginBodyContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onClickRegister: () -> Unit = {},
    onClickForgotPass: () -> Unit = {},
    onSucessLogin: () -> Unit = {}

) {
    val emailOrUser = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val auth = Firebase.auth
    val activity = LocalView.current.context as Activity

    val loginError =  remember { mutableStateOf("") }


    // Estados
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }







    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FCFF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text("INICIAR SESIÓN", fontSize = 26.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = emailOrUser.value,
            onValueChange = { emailOrUser.value = it },
            placeholder = { Text("Usuario o correo electrónico") },
            shape = RoundedCornerShape(50),
            visualTransformation =VisualTransformation.None,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if(emailError.isNotEmpty()){
                    Text(
                        text = emailError,
                        color = Color.Red
                    )
                }

            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = { Text("Contraseña") },
            shape = RoundedCornerShape(50),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if(passwordError.isNotEmpty()){
                    Text(
                        text = passwordError,
                        color = Color.Red
                    )
                }
            }

        )

        Spacer(modifier = Modifier.height(16.dp))

        if(loginError.value.isNotEmpty()){
            Text(
                loginError.value,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
        }



        OutlinedButton(
            onClick = {
                // Para validar el email y password
                val isValidEmail: Boolean = validateEmail(emailOrUser.value).first
                val isValidPassword = validatePassword(password.value).first

                emailError = validateEmail(emailOrUser.value).second
                passwordError = validatePassword(password.value).second

                if(isValidEmail && isValidPassword){
                    auth.signInWithEmailAndPassword(emailOrUser.value,password.value).
                    addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful){
                            onSucessLogin()
                        }else{
                            loginError.value = when(task.exception){
                                is FirebaseAuthInvalidCredentialsException -> "Correo o contraseña incorrecta"
                                is FirebaseAuthInvalidUserException -> "Usuario no existe"
                                else -> "Error al iniciar sesión. Intente de nuevo"
                            }
                        }
                    }

                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),

        ) {
            Text("Ingresar", fontWeight = FontWeight.SemiBold)
        }

        TextButton(
            onClick = onClickRegister

        ) {
            Text(
                "¿No tienes una cuenta? Registrate"
            )

        }

        TextButton(
            onClick = onClickForgotPass

        ) {
            Text(
                "¿Olvidó su contraseña?"
            )

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
