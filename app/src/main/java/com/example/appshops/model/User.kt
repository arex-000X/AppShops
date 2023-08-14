package com.example.appshops.model

import android.text.Editable


data class User(
    var id: Int = 0,
    var first_name: String = "",
    var last_name: String = "",
    var mail: String = "",
    var password: Editable? = null,
    var isOnline: Boolean = false,
)