package com.example.a221027_sarvesh_mrnelson_project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.a221027_sarvesh_mrnelson_project1.navigation.AppNavigation
import com.example.a221027_sarvesh_mrnelson_project1.ui.theme.Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab3Theme {
                AppNavigation()  //calls navigation system
            }
        }
    }
}