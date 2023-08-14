package com.example.appshops.authorization.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appshops.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthViewModel constructor(application: Application) : AndroidViewModel(application) {

    private lateinit var auth: FirebaseAuth
    val error: MutableLiveData<String> = MutableLiveData()
    val userFirebase: MutableLiveData<FirebaseUser> = MutableLiveData()

    init {
        loginViewModel()
    }

    fun loginViewModel() {
        auth = FirebaseAuth.getInstance()
    }

    fun getError(): LiveData<String> {
        return error
    }

    fun getUser(): LiveData<FirebaseUser> {
        return userFirebase
    }

    fun addUserFirebase(user: User) {
        try {
            auth.createUserWithEmailAndPassword(
                user.mail.trim(),
                user.password.toString().trim()
            )
                .addOnSuccessListener {

                    userFirebase.value = it.user

                }.addOnFailureListener {
                    error.value = it.message
                }
        } catch (exception: Exception) {
            error.value = exception.message
        }


    }

    fun signIn(
        email: String,
        password: String
    ) {

        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {

                    userFirebase.value = it.user

                }.addOnFailureListener {
                    error.value = it.message
                }

        } catch (exception: Exception) {
            error.value = exception.message
        }

    }

    fun resetPassword(email: String) {
        try {
            auth.sendPasswordResetEmail(email)
        } catch (exception: Exception) {
            error.value = exception.message
        }

    }

}



