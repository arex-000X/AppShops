package com.example.appshops.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.GlobalViewModel
import com.example.appshops.GlobalViewModelFactory
import com.example.appshops.R
import com.example.appshops.authorization.fragments.FragmentAuth
import com.example.appshops.main.adapter.AdapterRecylerPerson
import com.google.firebase.auth.FirebaseAuth

class FragmentPersonScreen : Fragment() {


    lateinit var adapterRecylerPerson: AdapterRecylerPerson
    lateinit var recylerViewPrson: RecyclerView
    lateinit var viewModelGlobal: GlobalViewModel
    lateinit var viewModelFactory: GlobalViewModelFactory




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_person_screen, container, false)
        viewModelFactory = GlobalViewModelFactory(requireActivity().supportFragmentManager)
        viewModelGlobal = ViewModelProvider(this,viewModelFactory).get(GlobalViewModel::class.java)
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
        adapterRecylerPerson.setOnClickListenerItem(object: AdapterRecylerPerson.OnClickListenerItem{
            override fun onClick(position: Int) {
             Log.d("MainLogShow","Clickitem")
                val menuitem = menu[position]
                when (menuitem){
                    "Log out"-> {
                        auth.signOut()
                        requireActivity().supportFragmentManager.popBackStack()
                        viewModelGlobal.replaceFragment(FragmentAuth(),false,R.id.fragment_container_view)
                    }
                }
            }
        })

    }




    fun initViews(view: View) {
        recylerViewPrson = view.findViewById(R.id.recyler_view_erson)
    }



    companion object{
        lateinit var auth: FirebaseAuth
    }
}