package com.example.appshops

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appshops.authorization.fragments.FragmentAuth
import com.example.appshops.authorization.viewmodel.GlobaViewModel
import com.example.appshops.main.fragments.FragmentHost
import com.example.appshops.manager.ManagerFragments
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), ManagerFragments {
    private val createFragmentAuth = FragmentAuth()
    private val createFragmentHost = FragmentHost()
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


    override fun createMainFragment(fragment: Fragment, container: Int) {
        val current = supportFragmentManager.findFragmentById(container)
        if (current == null) {
            supportFragmentManager.beginTransaction()
                .add(container, fragment).commit()
        }
    }


    override fun replaceFragment(fragment: Fragment, addToBackButton: Boolean, container: Int) {

        when (addToBackButton) {
            true -> addFragmentBackButton(fragment, container)
            false -> addFragment(fragment, container)

        }


    }

    override fun addFragment(fragment: Fragment, container: Int) {
        supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
    }

    //Переключение фрагмента и добавление возможности откатится назад по стеку
    override fun addFragmentBackButton(fragment: Fragment, container: Int) {
        var count = 0
        supportFragmentManager.beginTransaction()
            .addToBackStack("FragmentStack ${count++}")
            .replace(container, fragment)
            .commit()
    }


    override fun onStart() {
        super.onStart()
        auth.signOut()
        val current = auth.currentUser
        if (current == null) {
            createMainFragment(createFragmentAuth, R.id.fragment_container_view)
        } else {
            replaceFragment(createFragmentHost,false, R.id.fragment_container_view)
        }
    }
}


//------------------------------------------------------------------------------
