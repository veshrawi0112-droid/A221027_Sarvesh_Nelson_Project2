package com.example.a221027_sarvesh_mrnelson_project2.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.Locale

class LocationHelper(private val context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(onLocationReceived: (String) -> Unit) {
        // 1. Try to get last known location first (fastest)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                processLocation(location.latitude, location.longitude, onLocationReceived)
            } else {
                // 2. If lastLocation is null, request a fresh current location
                requestFreshLocation(onLocationReceived)
            }
        }.addOnFailureListener {
            onLocationReceived("Error: ${it.message}")
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestFreshLocation(onLocationReceived: (String) -> Unit) {
        val priority = Priority.PRIORITY_HIGH_ACCURACY
        fusedLocationClient.getCurrentLocation(priority, CancellationTokenSource().token)
            .addOnSuccessListener { location ->
                if (location != null) {
                    processLocation(location.latitude, location.longitude, onLocationReceived)
                } else {
                    onLocationReceived("Location unavailable. Enable GPS.")
                }
            }
    }

    private fun processLocation(lat: Double, lon: Double, onResult: (String) -> Unit) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lon, 1)

            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                val area = address.subLocality ?: address.locality ?: "Unknown Area"
                val state = address.adminArea ?: "Unknown State"
                onResult("$area, $state")
            } else {
                onResult("$lat, $lon")
            }
        } catch (e: Exception) {
            onResult("$lat, $lon")
        }
    }
}
