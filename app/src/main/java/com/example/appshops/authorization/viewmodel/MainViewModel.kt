package com.example.appshops.authorization.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainViewModel: ViewModel() {
    fun databaseRead() {
        val database = Firebase.database
        val myRef = database.getReference("user")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    for (dataSnapshot in snapshot.children) {

                    }
                } catch (error: Exception) {
                    Log.d("FirebaseValue", "Value is: $error")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ErrorFirebase", "Ошибка", error.toException())
            }

        })
    }
}