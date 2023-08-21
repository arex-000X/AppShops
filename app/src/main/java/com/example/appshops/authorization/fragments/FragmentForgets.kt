package com.example.appshops.authorization.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appshops.GlobalViewModel
import com.example.appshops.GlobalViewModelFactory
import com.example.appshops.R
import com.example.appshops.authorization.viewmodel.AuthViewModel
import com.example.appshops.manager.ManagerFragments

class FragmentForgets: Fragment() {

    private lateinit var mail_reset:EditText
    private lateinit var rest_btn:Button
    private var managerFragments:ManagerFragments? = null
    private lateinit var viewmodel:AuthViewModel
    lateinit var viewModelGlobal: GlobalViewModel
    lateinit var viewModelFactory: GlobalViewModelFactory
    override fun onAttach(context: Context) {
        super.onAttach(context)
        managerFragments = context as ManagerFragments
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = layoutInflater.inflate(R.layout.fragment_forgets,container,false)
        viewModelFactory = GlobalViewModelFactory(requireActivity().supportFragmentManager)
        viewModelGlobal = ViewModelProvider(this,viewModelFactory).get(GlobalViewModel::class.java)
        viewmodel = ViewModelProvider(this).get(AuthViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        clicker()



    }
    private fun clicker(){
        rest_btn.setOnClickListener {
            val mail = mail_reset.text
            viewmodel.resetPassword(mail.toString())
            managerFragments?.replaceFragment(FragmentAuth(),false,R.id.fragment_container_view)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        managerFragments = null
    }

    fun initViews(view:View){
        mail_reset = view.findViewById(R.id.mail_reset)
        rest_btn = view.findViewById(R.id.reset_btn)
    }
}