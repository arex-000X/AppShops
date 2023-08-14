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

class FragmentLogin : Fragment() {

    private var managerFragments: ManagerFragments? = null
    private lateinit var mailEditText: EditText
    private lateinit var passEditText: EditText
    private lateinit var login_btn: Button
    private lateinit var viewmodel: AuthViewModel
    private lateinit var forgets_password: TextView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        managerFragments = context as ManagerFragments
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_login, container, false)
        initView(view)
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
        managerFragments = null
    }

    private fun clicker() {
        login_btn.setOnClickListener {
            viewmodel.signIn(
                mailEditText.text.toString().trim(),
                passEditText.text.toString().trim()
            )
        }
        forgets_password.setOnClickListener {
            managerFragments?.replaceFragment(FragmentForgets(), true,R.id.fragment_container_view)

        }
    }

    private fun upadateUI() {
        viewmodel.getUser().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                managerFragments?.replaceFragment(FragmentHost(),false,R.id.fragment_container_view)
            }
        })
        viewmodel.getError().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(context, "Error: ${it}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun initView(view: View) {

        mailEditText = view.findViewById(R.id.mail_login)
        passEditText = view.findViewById(R.id.password_login)
        login_btn = view.findViewById(R.id.login_btn)
        forgets_password = view.findViewById(R.id.forgets_pass)
    }


}