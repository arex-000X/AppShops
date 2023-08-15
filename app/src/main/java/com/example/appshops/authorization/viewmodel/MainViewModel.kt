package com.example.appshops.authorization.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appshops.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database
    private val myRef = database.getReference("user")
    var listUser: MutableLiveData<List<User>> = MutableLiveData()
    fun databaseRead() {

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val userId = auth.currentUser ?: return
                    val list = ArrayList<User>()
                    for (dataSnapshot in snapshot.children) {
                        val user: User? = dataSnapshot.getValue(User::class.java)

                        if (!user?.id.equals(userId.uid))
                        list.add(user as User)
                    }
                    listUser.value = list
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