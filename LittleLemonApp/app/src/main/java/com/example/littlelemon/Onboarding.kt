package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.*


@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    var firstNameTxt by remember {mutableStateOf("")}
    var lastNameTxt by remember {mutableStateOf("")}
    var emailTxt by remember {mutableStateOf("")}

    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(
                    PrimaryColor1,
                    shape = RoundedCornerShape(0.dp)
                )
        ) {
            Text(
                stringResource(id = R.string.welcome_string),
                style = TextStyle(
                    fontSize = 24.sp,
                    color = SecondaryColor3,
                ),
                textAlign = TextAlign.Center,
                fontFamily = karla_regular,
            )
        }

        // Personal information
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 0.dp, top = 50.dp, bottom = 50.dp)
        ) {
            Text(
                stringResource(id = R.string.sub_title_onboarding),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = SecondaryColor4,
                    fontWeight = FontWeight.Bold,
                ),
                fontFamily = karla_regular,
                textAlign = TextAlign.Left,
            )
        }

        // First name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 0.dp, bottom = 30.dp)
        ) {
            Column {
                Text(
                    stringResource(id = R.string.first_name_onboarding),
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = SecondaryColor4,
                    ),
                    fontFamily = karla_regular,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .height(24.dp)
                )
                OutlinedTextField(
                    value = firstNameTxt,
                    onValueChange = {
                        firstNameTxt = it
                    },
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = karla_regular,),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                    ),
                    placeholder = { Text(fontSize = 15.sp ,text = "Tilly", color = Color.Gray)},
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        // Last name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 0.dp, bottom = 30.dp)
        ) {
            Column {
                Text(
                    stringResource(id = R.string.last_name_onboarding),
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = SecondaryColor4,
                    ),
                    fontFamily = karla_regular,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .height(24.dp)
                )
                OutlinedTextField(
                    value = lastNameTxt,
                    onValueChange = {
                        lastNameTxt = it
                    },
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = karla_regular,),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                    ),
                    placeholder = { Text(fontSize = 15.sp ,text = "Doe", color = Color.Gray) },
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        // Email
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 0.dp, bottom = 30.dp)
        ) {
            Column {
                Text(
                    stringResource(id = R.string.email_onboarding),
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = SecondaryColor4,
                    ),
                    fontFamily = karla_regular,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .height(24.dp)
                )
                OutlinedTextField(
                    value = emailTxt,
                    onValueChange = {
                        emailTxt = it
                    },
                    singleLine = true,
                    textStyle = TextStyle(fontFamily = karla_regular,),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                    ),
                    placeholder = { Text(fontSize = 15.sp ,text = "tillydoe@example.com", color = Color.Gray) },
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (errorMessage.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontFamily = karla_regular,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.fillMaxSize().height(16.dp))
            }

            if (successMessage.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = successMessage,
                        color = Color.Red,
                        fontFamily = karla_regular,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.fillMaxSize().height(16.dp))
            }

            Button(
                onClick = {
                    if ((firstNameTxt.isBlank() || lastNameTxt.isBlank() || emailTxt.isBlank())) {
                        errorMessage = "Registration unsuccessful. Please enter all data."
                        successMessage = ""
                    } else {
                        val editor = sharedPreferences.edit()
                        editor.putString("firstName", firstNameTxt)
                        editor.putString("lastName", lastNameTxt)
                        editor.putString("email", emailTxt)
                        editor.apply()

                        errorMessage = ""
                        successMessage = "Registration successful!"

                        navController.navigate(Home.route)
                    }
                },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor2),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 20.dp, end = 15.dp, top = 70.dp, bottom = 20.dp)
            ) {
                Text(
                    stringResource(id = R.string.button_register_onboarding),
                    fontSize = 18.sp,
                    fontFamily = karla_regular,
                    color = SecondaryColor4
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(navController = rememberNavController())
}
