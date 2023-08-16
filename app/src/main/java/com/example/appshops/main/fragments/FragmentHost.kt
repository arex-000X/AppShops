package com.example.appshops.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appshops.R
import com.example.appshops.manager.ManagerFragments
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentHost : Fragment() {
    private lateinit var bottomMenu: BottomNavigationView
    private var managerFragment: ManagerFragments? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        managerFragment = context as ManagerFragments

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_host, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        bottomMenu.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.page_1 ->
                    managerFragment?.replaceFragment(
                        FragmentMain(),
                        false,
                        R.id.fragment_container_menu
                    )

                R.id.page_2 ->
                    managerFragment?.replaceFragment(
                        FragmentLikeScreen(),
                        false,
                        R.id.fragment_container_menu
                    )

                R.id.page_3 ->
                    managerFragment?.replaceFragment(
                        FragmentShopScreen(),
                        false,
                        R.id.fragment_container_menu
                    )

                R.id.page_4 -> {
                    FragmentMessageScreen.currentUserId = currentUserId
                    managerFragment?.replaceFragment(
                        FragmentMessageScreen(),
                        false, R.id.fragment_container_menu
                    )
                }

                R.id.page_5 ->
                    managerFragment?.replaceFragment(
                        FragmentPersonScreen(),
                        false, R.id.fragment_container_menu
                    )
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        managerFragment = null
    }


    fun initViews(view: View) {
        bottomMenu = view.findViewById(R.id.bottomNav)
    }

    companion object {
        var currentUserId: String = "current_id"
    }


}