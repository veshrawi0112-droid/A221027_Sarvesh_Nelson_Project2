package com.example.a221027_sarvesh_mrnelson_project1.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a221027_sarvesh_mrnelson_project1.viewmodel.MainViewModel

// ---------------- LOGIN SCREEN ----------------

@Composable
fun LoginScreen(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "FoodLink Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = {
            navController.navigate("register")
        }) {
            Text("Create Account")
        }
    }
}

// ---------------- REGISTER SCREEN ----------------

@Composable
fun RegisterScreen(navController: NavController, viewModel: MainViewModel) {

    var ic by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            "Register Account",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = ic,
            onValueChange = { ic = it },
            label = { Text("IC Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                viewModel.updateProfile(
                    ic,
                    name,
                    location,
                    phone,
                    username,
                    password
                )

                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}

// ---------------- HOME SCREEN ----------------

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surface,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(14.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Search food or restaurants",
                        color = Color.Gray
                    )
                }
            }

            Column(Modifier.padding(16.dp)) {

                Text(
                    "Food Categories",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CategoryIcon("Meals", Icons.Default.Home)
                    CategoryIcon("Desserts", Icons.Default.Favorite)
                    CategoryIcon("Drinks", Icons.Default.Face)
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    "Food Near You",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                FoodCardList()
            }
        }
    }
}

// ---------------- FOOD CARD ----------------

// ---------------- FOOD CARD LIST ----------------

@Composable
fun FoodCardList() {

    Column {

        ExpandableFoodCard(
            title = "Restaurant Buffet Leftovers",
            location = "Sunway",
            time = "Pickup before 10PM",
            quantity = "15 meal packs available"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableFoodCard(
            title = "Bakery Unsold Cakes",
            location = "Subang",
            time = "Pickup before 8PM",
            quantity = "8 dessert boxes available"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableFoodCard(
            title = "Cafe Sandwich Packs",
            location = "Puchong",
            time = "Pickup before 6PM",
            quantity = "10 sandwich sets available"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableFoodCard(
            title = "Hotel Breakfast Extras",
            location = "KL City",
            time = "Pickup before 11AM",
            quantity = "20 breakfast meals available"
        )
    }
}

// ---------------- SINGLE EXPANDABLE CARD ----------------

@Composable
fun ExpandableFoodCard(
    title: String,
    location: String,
    time: String,
    quantity: String
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = {
            expanded = !expanded
        },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .animateContentSize()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Free pickup available",
                color = MaterialTheme.colorScheme.primary
            )

            if (expanded) {

                Spacer(modifier = Modifier.height(8.dp))

                Text("📍 $location")
                Text("⏰ $time")
                Text("🍱 $quantity")
            }
        }
    }
}

// ---------------- CATEGORY ICON ----------------

@Composable
fun CategoryIcon(label: String, icon: ImageVector) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            Modifier
                .size(72.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            label,
            fontWeight = FontWeight.Medium
        )
    }
}

// ---------------- BOTTOM NAVIGATION ----------------

@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("home")
            },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Home")
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("form")
            },
            icon = {
                Icon(Icons.Default.AddCircle, contentDescription = "Donate")
            },
            label = {
                Text("Donate")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("summary")
            },
            icon = {
                Icon(Icons.Default.List, contentDescription = "Summary")
            },
            label = {
                Text("Summary")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("profile")
            },
            icon = {
                Icon(Icons.Default.Person, contentDescription = "Profile")
            },
            label = {
                Text("Profile")
            }
        )
    }
}

// ---------------- DONATION FORM ----------------

@Composable
fun FoodFormScreen(navController: NavController, viewModel: MainViewModel) {

    var foodName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                "Food Donation Form",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text("Food Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.updateFood(foodName, quantity, location)
                    navController.navigate("preview")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Preview Donation")
            }
        }
    }
}

// ---------------- PREVIEW SCREEN ----------------

@Composable
fun FoodPreviewScreen(navController: NavController, viewModel: MainViewModel) {

    val data = viewModel.foodData.value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                "Food Preview",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(Modifier.padding(20.dp)) {

                    Text("🍱 Food: ${data.foodName}")
                    Text("📦 Quantity: ${data.quantity}")
                    Text("📍 Location: ${data.location}")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate("details")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm Donation")
            }
        }
    }
}

// ---------------- DETAILS SCREEN ----------------

@Composable
fun FoodDetailsScreen(navController: NavController, viewModel: MainViewModel) {

    val data = viewModel.foodData.value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                "Donation Successful",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(Modifier.padding(20.dp)) {

                    Text("🎉 Thank you for donating food!")

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Food: ${data.foodName}")
                    Text("Quantity: ${data.quantity}")
                    Text("Location: ${data.location}")
                }
            }
        }
    }
}

// ---------------- SUMMARY SCREEN ----------------

@Composable
fun SummaryScreen(navController: NavController, viewModel: MainViewModel) {

    val data = viewModel.foodData.value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                "Donation Summary",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(Modifier.padding(20.dp)) {

                    Text("Latest Donation")
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Food: ${data.foodName}")
                    Text("Quantity: ${data.quantity}")
                    Text("Location: ${data.location}")
                }
            }
        }
    }
}

// ---------------- PROFILE SCREEN ----------------

// ---------------- PROFILE SCREEN ----------------

@Composable
fun ProfileScreen(navController: NavController, viewModel: MainViewModel) {

    var editing by remember { mutableStateOf(false) }

    var ic by remember { mutableStateOf(viewModel.foodData.value.icNumber) }
    var name by remember { mutableStateOf(viewModel.foodData.value.fullName) }
    var location by remember { mutableStateOf(viewModel.foodData.value.userLocation) }
    var phone by remember { mutableStateOf(viewModel.foodData.value.phoneNumber) }
    var username by remember { mutableStateOf(viewModel.foodData.value.username) }
    var password by remember { mutableStateOf(viewModel.foodData.value.password) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                "Profile",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            // IC NUMBER
            OutlinedTextField(
                value = ic,
                onValueChange = { ic = it },
                label = { Text("IC Number") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // FULL NAME
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // LOCATION
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // PHONE NUMBER
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // USERNAME
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    if (editing) {

                        viewModel.updateProfile(
                            ic,
                            name,
                            location,
                            phone,
                            username,
                            password
                        )
                    }

                    editing = !editing
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                if (editing) {
                    Text("Save Details")
                } else {
                    Text("Edit Profile")
                }
            }
        }
    }
}