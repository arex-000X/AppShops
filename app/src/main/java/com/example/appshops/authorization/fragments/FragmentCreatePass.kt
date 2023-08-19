package com.example.appshops.authorization.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appshops.R
import com.example.appshops.authorization.viewmodel.AuthViewModel
import com.example.appshops.main.fragments.FragmentHost
import com.example.appshops.manager.ManagerFragments
import com.example.appshops.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FragmentCreatePass : Fragment() {

    private var managerFragment: ManagerFragments? = null
    private lateinit var textViewUserNames: TextView
    private lateinit var viewmodel: AuthViewModel
    private lateinit var passwordEditText: EditText
    private lateinit var createAccountButton: Button
    private lateinit var auth: FirebaseAuth
    override fun onAttach(context: Context) {
        super.onAttach(context)
        managerFragment = context as ManagerFragments
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.registration_password, container, false)
        initViews(view)
        auth = Firebase.auth
        viewmodel = ViewModelProvider(this).get(AuthViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicker()
        upadateUI()

    }

    override fun onDestroy() {
        super.onDestroy()
        managerFragment = null
    }


    private fun clicker() {
        val text = "${userModel.first_name},${userModel.last_name}"
        textViewUserNames.text = text
        userModel.password = passwordEditText.text
        createAccountButton.setOnClickListener {
            viewmodel.addUserFirebase(userModel)
        }

    }


    fun upadateUI() {
        viewmodel.getUser().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                managerFragment?.replaceFragment(
                    FragmentHost(),
                    false,
                    R.id.fragment_container_view
                )
            }
        })
        viewmodel.getError().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(context, "Error: ${it}", Toast.LENGTH_LONG).show()
            }

        })
    }


    fun initViews(view: View) {
        textViewUserNames = view.findViewById(R.id.textViewUserNames)
        createAccountButton = view.findViewById(R.id.create_account)
        passwordEditText = view.findViewById(R.id.password)
    }

    companion object {

        lateinit var userModel: User


        fun content(user: User) {
            userModel = user
        }


    }


}


















