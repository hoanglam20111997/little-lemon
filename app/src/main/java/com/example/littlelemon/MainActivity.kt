package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.littlelemon.ui.theme.LittleLemonTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var firstNameSaved = sharedPreferences.getString("firstName", "")
        var lastNameSaved = sharedPreferences.getString("lastName", "")
        var emailSaved = sharedPreferences.getString("email", "")

        Log.d("firstNameSaved", firstNameSaved.toString())
        Log.d("lastNameSaved", lastNameSaved.toString())
        Log.d("emailSaved", emailSaved.toString())

        setContent {
            LittleLemonTheme {
                MyNavigation(
                    sharedPreferences,
                    firstNameSaved.toString(),
                    lastNameSaved.toString(),
                    emailSaved.toString()
                )
            }
        }
    }
}

@Composable
fun MyNavigation(
    sharedPreferences: SharedPreferences,
    firstName: String,
    lastName: String,
    email: String,
) {
    val navController = rememberNavController()

    var route = Onboarding.route
    if (firstName != "" || lastName != "" || email != "") {
        route = Home.route
    }

    NavHost(
        navController = navController,
        startDestination = route,
    ) {
        composable(Onboarding.route) {
            Onboarding(navController = navController, sharedPreferences);
        }
        composable(Home.route) {
            HomeScreen(navController = navController, sharedPreferences);
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController, sharedPreferences);
        }
    }
}