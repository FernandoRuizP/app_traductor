package com.example.app_traductor.ui.navegacion

import ForgotPasswordScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_traductor.ui.pantallas.LoginScreen
import com.example.app_traductor.ui.pantallas.MainScreen
import com.example.app_traductor.ui.pantallas.RegisterScreen

@Composable

fun NavigationApp(){
    val myNavController = rememberNavController()
    val myStartDestination: String = "login"

    NavHost(
        navController = myNavController,
        startDestination = myStartDestination

    ){
        composable ("login"){
            LoginScreen(
                onClickRegister = {
                    myNavController.navigate("register")
                },
                onClickForgotPass = {
                    myNavController.navigate("forgotPass")
                },
                onSucessLogin = {
                    myNavController.navigate("mainScreen"){
                        popUpTo("login"){inclusive = true}
                    }
                }


            )
        }

        composable ("register"){
            RegisterScreen(
                onClickBack = {
                    myNavController.navigate("login")
                },
                onClickLogin = {
                    myNavController.navigate("login")
                },
                onSuccessfulRegister = {
                    myNavController.navigate("mainScreen"){
                        popUpTo(0)
                    }
                }

            )
        }
        composable ("forgotPass"){
            ForgotPasswordScreen(
                onClickBack = {
                    myNavController.navigate("login")
                },
            )
        }
        composable ("mainScreen"){
            MainScreen()
        }
    }
}
