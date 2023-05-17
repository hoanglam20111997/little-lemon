@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.*
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

private val httpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json(contentType = ContentType("text", "plain"))
    }
}

@Composable
fun HomeScreen(navController: NavController, database: AppDatabase) {
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    // add orderMenuItems variable here
    var orderMenuItems by remember { mutableStateOf(false) }

    // add menuItems variable here
    val menuItems = if (orderMenuItems) {
        databaseMenuItems.sortedBy { it.title }
    } else {
        databaseMenuItems
    }

    val categoryNames: List<String> = menuItems.map { it.category }.distinct()
    var selectedCategory by remember { mutableStateOf("") }

    var searchPhrase by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(0.72f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(200.dp)
                        .height(80.dp)
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            navController.navigate(Profile.route)
                        }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    PrimaryColor1,
                    shape = RoundedCornerShape(0.dp)
                )
                .padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 15.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.brand_home),
                    style = TextStyle(
                        fontSize = 64.sp,
                        color = PrimaryColor2,
                        fontFamily = markazitext_regular,
                    ),
                    modifier = Modifier
                        .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
                        .height(60.dp)
                )

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row {
                        Column (
                            modifier = Modifier.fillMaxWidth(0.6f)
                        ) {
                            Text(
                                text = stringResource(id = R.string.city_home),
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    color = SecondaryColor3,
                                    fontFamily = markazitext_regular,
                                ),
                            )
                            Text(
                                text = stringResource(id = R.string.short_description_home),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = SecondaryColor3,
                                    fontFamily = karla_regular,
                                ),
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 35.dp, start = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hero_image),
                                contentDescription = "Upper Panel Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(130.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp)))
                        }
                    }
                }

                // Search bar
                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    placeholder = { Text("Enter Search Phrase", color = SecondaryColor4) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = SecondaryColor4
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                    ,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = SecondaryColor4,
                        backgroundColor = SecondaryColor3,
                        focusedBorderColor = PrimaryColor2,
                        cursorColor = PrimaryColor2,
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp, start = 15.dp, end = 15.dp)
            ,
            contentAlignment = Alignment.CenterStart
        ) {
            Column(

            ) {
                Text(
                    text = stringResource(id = R.string.order_delivery_home),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = karla_regular,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Row {
                    val capitalizedCategoryNames = categoryNames.map { it.replaceFirstChar(Char::uppercaseChar) }

                    // Menu breakdown buttons
                    capitalizedCategoryNames.forEach { categoryName ->
                        Button(
                            onClick = {
                                if (selectedCategory == categoryName) {
                                    selectedCategory = "";
                                } else {
                                    selectedCategory = categoryName
                                }
                            },
                            modifier = Modifier.padding(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (categoryName == selectedCategory) Color.Gray else Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(text = categoryName)
                        }
                    }
                }
            }
        }

        var filteredMenuItemsOrder = if (selectedCategory.isNotEmpty()) {
            menuItems.filter{ it.category.contains(selectedCategory, ignoreCase = true)}
        } else {
            menuItems
        }

        if (searchPhrase.isNotEmpty()) {
            // Filter the menu items based on the search phrase
            val filteredMenuItems = filteredMenuItemsOrder.filter{ it.title.contains(searchPhrase, ignoreCase = true) }
            MenuItemsList(filteredMenuItems)
        } else {
            MenuItemsList(filteredMenuItemsOrder)
        }
    }

    LaunchedEffect(Unit) {
        loadDataFromNetworkAndSaveToDatabase(database)
    }
}


@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    Divider(
        color = Color.Gray,
        thickness = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, end = 15.dp, bottom = 0.dp, start = 15.dp)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                ) {
                    Text(
                        text = menuItem.title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = SecondaryColor4,
                            fontFamily = karla_regular,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.75f)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(380.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = menuItem.description,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = PrimaryColor1,
                                        fontFamily = karla_regular,
//                                        lineHeight = (16.sp * 1.5)
                                    ),
                                    modifier = Modifier
                                        .padding(top = 10.dp),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Text(
                                text =  "$" + menuItem.price.toString(),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = PrimaryColor1,
                                    fontFamily = karla_regular,
                                    fontWeight = FontWeight.Bold,
                                ),
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 0.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {

                            GlideImage(
                                model = menuItem.image,
                                contentDescription = "Image",
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }

                    Divider(
                        color = SecondaryColor3,
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, bottom = 5.dp)
                    )
                }
            }
        )
    }
}

private suspend fun loadDataFromNetworkAndSaveToDatabase(database: AppDatabase) {
    withContext(Dispatchers.IO) {
        if (database.menuItemDao().isEmpty()) {
            try {
                val menuItems = fetchMenu()
                saveMenuToDatabase(menuItems, database)
            } catch (e: Exception) {
                // Handle network error
            }
        }
    }
}

private suspend fun fetchMenu(): List<MenuItemNetwork> {
    val menuJson = httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").bodyAsText()

    val jsonMenu = JSONObject(menuJson)
    val jsonMenuArray = jsonMenu.getJSONArray("menu")

    val menuItems = mutableListOf<MenuItemNetwork>()

    for (i in 0 until jsonMenuArray.length()) {
        val jsonMenuItem = jsonMenuArray.getJSONObject(i)

        val id = jsonMenuItem.getInt("id")
        val title = jsonMenuItem.getString("title")
        val description = jsonMenuItem.getString("description")
        val price = jsonMenuItem.getDouble("price")
        val image = jsonMenuItem.getString("image")
        val category = jsonMenuItem.getString("category")

        val menuItem = MenuItemNetwork(id, title, description, price, image, category)
        menuItems.add(menuItem)
    }

    return menuItems
}

private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>, database: AppDatabase) {
    val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
    database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
}

@Preview(showBackground = true)
@Composable
fun HomeScreenReview() {
    val context = LocalContext.current
    val database = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    HomeScreen(navController = rememberNavController(), database = database)
}