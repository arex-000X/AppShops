package com.example.appshops.main.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R
import com.example.appshops.main.adapter.AdapterRecyler
import com.example.appshops.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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
        adapterMassage = AdapterRecyler()
        recylerView.layoutManager = LinearLayoutManager(context)
        recylerView.adapter = adapterMassage
        databaseRead()
        adapterMassage.setOnClickItemListener(object : AdapterRecyler.OnClicklistener {
            override fun onUserClickIte(user: User) {
                Log.d("MessageScreen", "Onbclick")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun databaseRead() {
        val database = Firebase.database
        val myRef = database.getReference("user")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    for (dataSnapshot in snapshot.children) {

                    }
                } catch (error: Exception) {
                    Log.d("FirebaseValue", "Value is: $error")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ErrorFirebase", "Ошибка", error.toException())
            }

        })
    }

    fun initViews(view: View) {
        recylerView = view.findViewById(R.id.recylerview)
    }
}