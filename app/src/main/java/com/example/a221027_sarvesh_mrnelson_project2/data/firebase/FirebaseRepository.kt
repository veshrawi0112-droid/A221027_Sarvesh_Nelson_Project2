package com.example.a221027_sarvesh_mrnelson_project2.data.firebase

import com.example.a221027_sarvesh_mrnelson_project2.data.FoodDonation
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()

    fun addDonation(foodDonation: FoodDonation) {

        db.collection("donations")
            .add(foodDonation)
            .addOnSuccessListener {
                println("Donation Saved")
            }
            .addOnFailureListener {
                println("Error: ${it.message}")
            }
    }

    fun getDonations(
        onResult: (List<FoodDonation>) -> Unit
    ) {

        db.collection("donations")
            .get()
            .addOnSuccessListener { result ->

                val donations =
                    result.toObjects(
                        FoodDonation::class.java
                    )

                onResult(donations)
            }
            .addOnFailureListener {

                onResult(emptyList())
            }
    }
}