package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.PrimaryColor1
import com.example.littlelemon.ui.theme.PrimaryColor2
import com.example.littlelemon.ui.theme.SecondaryColor3
import com.example.littlelemon.ui.theme.SecondaryColor4

@Composable
fun Onboarding() {
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
                fontFamily = FontFamily.SansSerif,
            )
        }

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
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Left,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 0.dp, bottom = 20.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {

                },
                label = { Text(stringResource(id = R.string.first_name_onboarding)) },
                placeholder = { Text(text = "Tilly") },

                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 0.dp, bottom = 20.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {

                },
                label = { Text(stringResource(id = R.string.last_name_onboarding)) },
                placeholder = { Text(text = "Doe") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 0.dp, bottom = 20.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {

                },
                label = { Text(stringResource(id = R.string.email_onboarding)) },
                placeholder = { Text(text = "tillydoe@example.com") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = { },
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor2),
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 15.dp, top = 70.dp, bottom = 20.dp)
        ) {
            Text(
                stringResource(id = R.string.button_register_onboarding),
                fontSize = 18.sp,
                color = SecondaryColor4
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnboardPreview() {
    Onboarding()
}