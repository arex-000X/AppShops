package com.example.appshops.main.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R
import com.example.appshops.authorization.viewmodel.MainViewModel
import com.example.appshops.main.adapter.AdapterMessage
import com.example.appshops.model.Message as Message1

class FragmentChat : Fragment() {

    private lateinit var userNameTitle_chat: TextView
    private lateinit var statUserView: View
    private lateinit var inputSend: EditText
    private lateinit var recylerview: RecyclerView
    private lateinit var viewModel: MainViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragmernt_chat, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniViews(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recylerview.layoutManager = LinearLayoutManager(context)
        viewModel.userModel.observe(viewLifecycleOwner, Observer {
            userNameTitle_chat.text = "${it.first_name},${it.last_name}"
            var isResursOnline = 0
            if (it.isOnline) {
                isResursOnline = R.drawable.circle_green
            } else
                isResursOnline = R.drawable.circle_red

            val drawable: Drawable? = ContextCompat.getDrawable(requireContext(), isResursOnline)
            statUserView.background = drawable
        })
        val messages = ArrayList<Message1>()
        for (i in 1..10){
            val message = Message1("test$i", currentId, currentOthersId)
            messages.add(message)
        }
        for (i in 1..10){
            val message = Message1("test$i", currentId, currentOthersId)
            messages.add(message)
        }
        val adapterMessage = AdapterMessage(currentId)
        recylerview.adapter = adapterMessage
        adapterMessage.setMessage(messages)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun iniViews(view: View) {
        userNameTitle_chat = view.findViewById(R.id.userNameTitle_chats)
        statUserView = view.findViewById(R.id.status_users)
        inputSend = view.findViewById(R.id.inputSend)
        recylerview = view.findViewById(R.id.recyclerViewChats)
    }

    companion object{
       var currentId:String = "curent_id"
       var currentOthersId:String = "curentUser_id"
       fun contentFragment(currentId:String,currentOthersId:String){
           this.currentId = currentId
           this.currentOthersId = currentOthersId


       }
    }
}