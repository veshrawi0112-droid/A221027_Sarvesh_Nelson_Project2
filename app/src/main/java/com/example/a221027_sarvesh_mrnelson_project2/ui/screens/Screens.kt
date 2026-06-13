package com.example.a221027_sarvesh_mrnelson_project2.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a221027_sarvesh_mrnelson_project2.viewmodel.MainViewModel
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.a221027_sarvesh_mrnelson_project2.data.location.LocationHelper
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
// ---------------- LOGIN SCREEN ----------------

@Composable
fun LoginScreen(navController: NavController, viewModel: MainViewModel) {

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
            value = viewModel.loginUsername.value,
            onValueChange = { viewModel.loginUsername.value = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.loginPassword.value,
            onValueChange = { viewModel.loginPassword.value = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.updateLogin(viewModel.loginUsername.value, viewModel.loginPassword.value)
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
            value = viewModel.regIc.value,
            onValueChange = { viewModel.regIc.value = it },
            label = { Text("IC Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.regName.value,
            onValueChange = { viewModel.regName.value = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.regLocation.value,
            onValueChange = { viewModel.regLocation.value = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.regPhone.value,
            onValueChange = { viewModel.regPhone.value = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.regUsername.value,
            onValueChange = { viewModel.regUsername.value = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.regPassword.value,
            onValueChange = { viewModel.regPassword.value = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.registerUser(
                    viewModel.regIc.value,
                    viewModel.regName.value,
                    viewModel.regLocation.value,
                    viewModel.regPhone.value,
                    viewModel.regUsername.value,
                    viewModel.regPassword.value
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
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    var searchText by remember {
        mutableStateOf("")
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {

            // =====================================
            // SEARCH API SECTION
            // =====================================

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    )
                    .padding(16.dp)
            ) {

                Column {

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        label = {
                            Text("Search Food")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Button(
                        onClick = {

                            if (searchText.isNotBlank()) {

                                viewModel.searchFoodApi(
                                    searchText
                                )

                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text("Search API")

                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    if (viewModel.apiResults.isNotEmpty()) {

                        Text(
                            "Search Results",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        viewModel.apiResults.forEach { meal ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {

                                Text(
                                    text = meal,
                                    modifier = Modifier.padding(16.dp)
                                )

                            }
                        }
                    }
                }
            }

            // =====================================
            // FOOD CATEGORIES
            // =====================================

            Column(
                Modifier.padding(16.dp)
            ) {

                Text(
                    "Food Categories",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceEvenly
                ) {

                    CategoryIcon(
                        "Meals",
                        Icons.Default.Home
                    )

                    CategoryIcon(
                        "Desserts",
                        Icons.Default.Favorite
                    )

                    CategoryIcon(
                        "Drinks",
                        Icons.Default.Face
                    )
                }

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                // =====================================
                // FOOD NEAR YOU
                // =====================================

                Text(
                    "Food Near You",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                FoodCardList()
            }
        }
    }
}
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

    var expanded by rememberSaveable { mutableStateOf(false) }

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
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "Summary"
                )
            },
            label = {
                Text("Summary")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("nearby")
            },
            icon = {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Nearby"
                )
            },
            label = {
                Text("Nearby")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("profile")
            },
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile"
                )
            },
            label = {
                Text("Profile")
            }
        )
    }
}

// ---------------- DONATION FORM ----------------

@Composable
fun FoodFormScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    val context = LocalContext.current

    val locationHelper = LocationHelper(context)

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {

                locationHelper.getCurrentLocation { location ->

                    viewModel.foodLocationInput.value = location
                }
            }
        }

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
                text = "Food Donation Form",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.foodNameInput.value,
                onValueChange = {
                    viewModel.foodNameInput.value = it
                },
                label = {
                    Text("Food Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.foodQuantityInput.value,
                onValueChange = {
                    viewModel.foodQuantityInput.value = it
                },
                label = {
                    Text("Quantity")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.foodLocationInput.value,
                onValueChange = {
                    viewModel.foodLocationInput.value = it
                },
                label = {
                    Text("Location")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {

                    if (
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        locationHelper.getCurrentLocation { location ->

                            viewModel.foodLocationInput.value = location
                        }

                    } else {

                        permissionLauncher.launch(
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Get Current Location")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    viewModel.updateFood(
                        viewModel.foodNameInput.value,
                        viewModel.foodQuantityInput.value,
                        viewModel.foodLocationInput.value
                    )

                    viewModel.saveDonationToFirebase()

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

    val data = viewModel.foodDonation.value

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

    val data = viewModel.foodDonation.value

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

    val data = viewModel.foodDonation.value

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
// ---------------- FOOD SEARCH SCREEN ----------------
// ==================================================
// NEARBY DONATIONS SCREEN
// ==================================================

@Composable
fun NearbyDonationsScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.loadDonations()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            item {

                Text(
                    text = "Nearby Food Donations",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            items(viewModel.donationsList) { donation ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Food: ${donation.foodName}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Quantity: ${donation.quantity}"
                        )

                        Text(
                            text = "Location: ${donation.location}"
                        )
                    }
                }
            }
        }
    }
}

// ---------------- PROFILE SCREEN ----------------

@Composable
fun ProfileScreen(navController: NavController, viewModel: MainViewModel) {

    var editing by rememberSaveable { mutableStateOf(false) }

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
                value = if (editing) viewModel.editIc.value else viewModel.userProfile.value.icNumber,
                onValueChange = { viewModel.editIc.value = it },
                label = { Text("IC Number") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // FULL NAME
            OutlinedTextField(
                value = if (editing) viewModel.editName.value else viewModel.userProfile.value.fullName,
                onValueChange = { viewModel.editName.value = it },
                label = { Text("Full Name") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // LOCATION
            OutlinedTextField(
                value = if (editing) viewModel.editLocation.value else viewModel.userProfile.value.userLocation,
                onValueChange = { viewModel.editLocation.value = it },
                label = { Text("Location") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // PHONE NUMBER
            OutlinedTextField(
                value = if (editing) viewModel.editPhone.value else viewModel.userProfile.value.phoneNumber,
                onValueChange = { viewModel.editPhone.value = it },
                label = { Text("Phone Number") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // USERNAME
            OutlinedTextField(
                value = if (editing) viewModel.editUsername.value else viewModel.loginData.value.username,
                onValueChange = { viewModel.editUsername.value = it },
                label = { Text("Username") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // PASSWORD
            OutlinedTextField(
                value = if (editing) viewModel.editPassword.value else viewModel.loginData.value.password,
                onValueChange = { viewModel.editPassword.value = it },
                label = { Text("Password") },
                enabled = editing,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (!editing) {
                        // Entering edit mode: Initialize draft values from current profile
                        viewModel.editIc.value = viewModel.userProfile.value.icNumber
                        viewModel.editName.value = viewModel.userProfile.value.fullName
                        viewModel.editLocation.value = viewModel.userProfile.value.userLocation
                        viewModel.editPhone.value = viewModel.userProfile.value.phoneNumber
                        viewModel.editUsername.value = viewModel.loginData.value.username
                        viewModel.editPassword.value = viewModel.loginData.value.password
                    } else {
                        // Saving: Update profile with draft values
                        viewModel.registerUser(
                            viewModel.editIc.value,
                            viewModel.editName.value,
                            viewModel.editLocation.value,
                            viewModel.editPhone.value,
                            viewModel.editUsername.value,
                            viewModel.editPassword.value
                        )

                        viewModel.saveUserToRoom()
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
