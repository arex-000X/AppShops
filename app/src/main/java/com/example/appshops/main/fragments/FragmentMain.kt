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

class FragmentMain : Fragment() {


    private lateinit var bottomMenu: BottomNavigationView
    private val managerFragments = context as ManagerFragments?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_main, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        bottomMenu.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.page_1 ->
                    managerFragments?.replaceFragment(FragmentMain(), false)

                R.id.page_2 ->
                    managerFragments?.replaceFragment(FragmentLikeScreen(), false)

                R.id.page_3 ->
                    managerFragments?.replaceFragment(FragmentShopScreen(), false)

                R.id.page_4 ->
                    managerFragments?.replaceFragment(
                        FragmentMessageScreen(),
                        false
                    )

                R.id.page_5 ->
                    managerFragments?.replaceFragment(
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

}


