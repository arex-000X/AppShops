package com.example.appshops.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R
import com.example.appshops.main.adapter.AdapterRecylerPerson

class FragmentPersonScreen : Fragment() {


    lateinit var adapterRecylerPerson: AdapterRecylerPerson
    lateinit var recylerViewPrson: RecyclerView




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_person_screen, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        adapterRecylerPerson = AdapterRecylerPerson()
        recylerViewPrson.layoutManager = LinearLayoutManager(context)
        recylerViewPrson.adapter = adapterRecylerPerson
        val menu = ArrayList<String>()
        menu.add("Shopping cart")
        menu.add("Setting")
        menu.add("Help")
        menu.add("Log out")
        adapterRecylerPerson.menu(menu)

    }




    fun initViews(view: View) {
        recylerViewPrson = view.findViewById(R.id.recyler_view_erson)
    }
}