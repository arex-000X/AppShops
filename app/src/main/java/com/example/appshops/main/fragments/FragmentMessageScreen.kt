package com.example.appshops.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R
import com.example.appshops.authorization.viewmodel.MainViewModel
import com.example.appshops.main.adapter.AdapterRecyler
import com.example.appshops.manager.ManagerFragments
import com.example.appshops.model.User


class FragmentMessageScreen : Fragment() {


    private lateinit var recylerView: RecyclerView
    private lateinit var adapterMassage: AdapterRecyler
    lateinit var viewModel: MainViewModel
    private var managerFragments:ManagerFragments? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        managerFragments = context as ManagerFragments
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initViews(view)
        adapterMassage = AdapterRecyler()
        recylerView.layoutManager = LinearLayoutManager(context)
        recylerView.adapter = adapterMassage
        viewModel.databaseRead()
        viewModel.listUser.observe(viewLifecycleOwner, Observer {
            adapterMassage.setUser(it)
        })
        adapterMassage.setOnClickItemListener(object : AdapterRecyler.OnClicklistener {
            override fun onUserClickIte(user: User) {
                FragmentChat.contentFragment(currentUserId,user.id.toString())
                managerFragments?.replaceFragment(FragmentChat(),true,R.id.fragment_container_view)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        managerFragments = null
    }

    fun initViews(view: View) {
        recylerView = view.findViewById(R.id.recylerview)
    }
    companion object{
        var currentUserId:String = ""
    }
}