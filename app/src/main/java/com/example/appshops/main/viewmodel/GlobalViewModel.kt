package com.example.appshops.main.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GlobalViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val database = Firebase.database
    private val referenceFirebaseUsers = database.getReference("user")
    fun setUserOnline(isOnline: Boolean) {
        auth = Firebase.auth
        val current = auth.currentUser
        if (current != null) {
            referenceFirebaseUsers.child(current.toString()).child("online").setValue(isOnline)
        }
    }
}