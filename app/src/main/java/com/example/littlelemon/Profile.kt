package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Screen",
            fontSize = 32.sp
        )
        Button(
            onClick = {
                val editor = sharedPreferences.edit()
                editor.putString("firstName", "")
                editor.putString("lastName", "")
                editor.putString("email", "")
                editor.apply()

                navController.navigate(Onboarding.route)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF4CE14)
            )
        ) {
            Text(
                text = "Onboarding Screen",
                fontSize = 24.sp
            )
        }
    }
}