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

class FragmentHost : Fragment(), ManagerFragments {
    private lateinit var bottomMenu: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createMainFragment(FragmentMain())


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


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
                    replaceFragment(FragmentMain(), false)

                R.id.page_2 ->
                    replaceFragment(FragmentLikeScreen(), false)

                R.id.page_3 ->
                    replaceFragment(FragmentShopScreen(), false)

                R.id.page_4 ->
                    replaceFragment(
                        FragmentMessageScreen(),
                        false
                    )

                R.id.page_5 ->
                    replaceFragment(
                        FragmentPersonScreen(),
                        false
                    )
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun initViews(view: View) {
        bottomMenu = view.findViewById(R.id.bottomNav)
    }

    override fun createMainFragment(fragment: Fragment) {
        val current =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container_menu)
        if (current == null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_menu, fragment)
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
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_menu, fragment)
            .commit()
    }

    override fun addFragmentBackButton(fragment: Fragment) {
        var count = 0
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack("FragmentStack ${count++}")
            .replace(R.id.fragment_container_menu, fragment)
            .commit()
    }


}