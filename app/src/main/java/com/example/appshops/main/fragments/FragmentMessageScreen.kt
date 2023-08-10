package com.example.appshops.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.appshops.R
import com.example.appshops.main.adapter.AdapterRecyler
import com.example.appshops.model.User


class FragmentMessageScreen : Fragment() {


    private lateinit var recylerView: RecyclerView
    private lateinit var adapterMassage: AdapterRecyler

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
        val view = layoutInflater.inflate(R.layout.fragment_message_screen, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        recylerView.layoutManager = LinearLayoutManager(context)
        recylerView.adapter = adapterMassage


        val userArray = ArrayList<User>()
        for (i: Int in 1..30) {
            val user = User(id = i, first_name = "Andrew", last_name = "Karaew", isOnline = true)
            userArray.add(user)
        }
        adapterMassage.setUser(userArray)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun initViews(view: View) {
        recylerView = view.findViewById(R.id.recylerview)
    }
}