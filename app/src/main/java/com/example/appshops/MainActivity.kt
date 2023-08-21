package com.example.appshops

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appshops.authorization.fragments.FragmentAuth
import com.example.appshops.main.fragments.FragmentHost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(){
    private val createFragmentAuth = FragmentAuth()
    private val createFragmentHost = FragmentHost()
    private lateinit var auth: FirebaseAuth
    lateinit var viewModel: GlobalViewModel
    lateinit var viewModelFactory: GlobalViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = GlobalViewModelFactory(supportFragmentManager)
        viewModel = ViewModelProvider(this,viewModelFactory).get(GlobalViewModel::class.java)
        viewModel.createMainFragment(createFragmentAuth,R.id.fragment_container_view)
        auth = Firebase.auth
        setContentView(R.layout.activity_main)
        //Реализация прозрачного статус бара-------------------------------------
            /*window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )*/
        //-----------------------------------------------------------------------
    }
    override fun onStart() {
        super.onStart()
        auth.signOut()
        val current = auth.currentUser
        if (current == null) {
           viewModel.createMainFragment(createFragmentAuth, R.id.fragment_container_view)
        } else {
            viewModel.replaceFragment(createFragmentHost,false, R.id.fragment_container_view)
        }
    }
}


//------------------------------------------------------------------------------
