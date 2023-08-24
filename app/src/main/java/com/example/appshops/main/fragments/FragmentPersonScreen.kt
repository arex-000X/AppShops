package com.example.appshops.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appshops.R
import com.example.appshops.main.adapter.AdapterRecylerPerson

class FragmentPersonScreen:Fragment() {


   lateinit var adapterRecylerPerson:AdapterRecylerPerson

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
        val view = layoutInflater.inflate(R.layout.fragment_person_screen,container,false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        val menu = ArrayList<String>()
        menu.add("Shopping cart")
        menu.add("setting")
        menu.add("help")
        menu.add("Log out")
        adapterRecylerPerson.menu(menu)



    }

    override fun onDestroy() {
        super.onDestroy()
    }



    fun initViews(view: View){

    }
}