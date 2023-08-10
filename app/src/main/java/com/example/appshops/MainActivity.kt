package com.example.appshops

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appshops.authorization.fragments.FragmentAuth
import com.example.appshops.authorization.viewmodel.GlobaViewModel
import com.example.appshops.main.fragments.FragmentMain
import com.example.appshops.manager.ManagerFragments
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), ManagerFragments {
    private val createFragmentAuth = FragmentAuth()
    private val createFragmentMain = FragmentMain()
    lateinit var viewModel: GlobaViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(GlobaViewModel::class.java)
        val fragmentManager = supportFragmentManager
        viewModel
        //Реализация прозрачного статус бара-------------------------------------
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        //-----------------------------------------------------------------------

    }


    override fun createMainFragment(fragment: Fragment) {
        val current = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if (current == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    override fun replaceFragment(fragment: Fragment, addToBackButton: Boolean) {

        when (addToBackButton) {
            true -> addFragmentBackButton(fragment)
            false -> addFragment(fragment)
        }


    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

    //Переключение фрагмента и добавление возможности откатится назад по стеку
    override fun addFragmentBackButton(fragment: Fragment) {
        var count = 0
        supportFragmentManager.beginTransaction()
            .addToBackStack("FragmentStack ${count++}")
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }


    override fun onStart() {
        super.onStart()
        val current = auth.currentUser
        if (current == null) {
            createMainFragment(createFragmentAuth)
        } else {
            createMainFragment(createFragmentMain)
        }
    }


}


//------------------------------------------------------------------------------
