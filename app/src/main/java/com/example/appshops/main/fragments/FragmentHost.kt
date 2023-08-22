package com.example.appshops.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appshops.GlobalViewModel
import com.example.appshops.GlobalViewModelFactory
import com.example.appshops.R
import com.example.appshops.main.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FragmentHost : Fragment() {
    private lateinit var bottomMenu: BottomNavigationView
    lateinit var viewModel:MainViewModel
    private lateinit var auth: FirebaseAuth
    lateinit var viewModelGlobal: GlobalViewModel
    lateinit var viewModelFactory: GlobalViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_host, container, false)
        viewModelFactory = GlobalViewModelFactory(requireActivity().supportFragmentManager)
        viewModelGlobal = ViewModelProvider(this,viewModelFactory).get(GlobalViewModel::class.java)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        auth = Firebase.auth
        bottomMenu.setOnItemSelectedListener(object: NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.page_1 ->
                        viewModelGlobal.replaceFragment(
                            FragmentMain(),
                            false,
                            R.id.fragment_container_menu
                        )

                    R.id.page_2 ->
                        viewModelGlobal.replaceFragment(
                            FragmentLikeScreen(),
                            false,
                            R.id.fragment_container_menu
                        )

                    R.id.page_3 ->
                        viewModelGlobal.replaceFragment(
                            FragmentShopScreen(),
                            false,
                            R.id.fragment_container_menu
                        )

                    R.id.page_4 -> {
                        FragmentMessageScreen.currentUserId = auth.uid.toString()
                        viewModelGlobal.replaceFragment(
                            FragmentMessageScreen(),
                            false, R.id.fragment_container_menu
                        )
                    }

                    R.id.page_5 ->
                        viewModelGlobal.replaceFragment(
                            FragmentPersonScreen(),
                            false, R.id.fragment_container_menu
                        )
                }
                return true
            }


        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.setUserOnline(false)
        Log.d("MainActivityCycle","FragentHost: onStop")
    }

    override fun onResume() {
        super.onResume()
        viewModel.setUserOnline(true)
        Log.d("MainActivityCycle","FragentHost: onResume")
    }
    fun initViews(view: View) {
        bottomMenu = view.findViewById(R.id.bottomNav)
    }
    companion object {
        var currentUserId: String = "curren_Id"
    }


}