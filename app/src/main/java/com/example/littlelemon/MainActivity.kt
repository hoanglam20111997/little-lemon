package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import org.json.JSONObject

class MainActivity : ComponentActivity() {
//    private val httpClient = HttpClient(Android) {
//        install(ContentNegotiation) {
//            json(contentType = ContentType("text", "plain"))
//        }
//    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var firstNameSaved = sharedPreferences.getString("firstName", "")
        var lastNameSaved = sharedPreferences.getString("lastName", "")
        var emailSaved = sharedPreferences.getString("email", "")

        setContent {
            LittleLemonTheme {
                MyNavigation(
                    firstNameSaved.toString(),
                    lastNameSaved.toString(),
                    emailSaved.toString(),
                    database
                )
            }
        }
    }


}

@Composable
fun MyNavigation(
    firstName: String,
    lastName: String,
    email: String,
    database: AppDatabase,
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
            Onboarding(navController = navController);
        }
        composable(Home.route) {
            HomeScreen(navController = navController, database);
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController);
        }
    }
}