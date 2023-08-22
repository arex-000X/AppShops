package com.example.appshops.authorization.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appshops.GlobalViewModel
import com.example.appshops.GlobalViewModelFactory
import com.example.appshops.R
import com.example.appshops.model.User

class FragmentAuth : Fragment() {



    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var mail: EditText
    private lateinit var signIn: Button
    private lateinit var logIn: TextView
    lateinit var viewModelGlobal: GlobalViewModel
    lateinit var viewModelFactory: GlobalViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_auth, container, false)
        viewModelFactory = GlobalViewModelFactory(requireActivity().supportFragmentManager)
        viewModelGlobal = ViewModelProvider(this,viewModelFactory).get(GlobalViewModel::class.java)
        initViews(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cliker()


    }

    private fun cliker(){
        logIn.setOnClickListener {
            viewModelGlobal.replaceFragment(FragmentLogin(), true,R.id.fragment_container_view)
        }
        signIn.setOnClickListener {
            viewModelGlobal.replaceFragment(FragmentCreatePass(),true,R.id.fragment_container_view)


            val userModel = User(
                first_name = firstName.text.toString(),
                last_name = lastName.text.toString(),
                mail = mail.text.toString()
            )
            FragmentCreatePass.content(userModel)
        }
    }



    private fun initViews(view: View) {
        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)
        mail = view.findViewById(R.id.mail)
        signIn = view.findViewById(R.id.singInButton)
        logIn = view.findViewById(R.id.login)
    }


}