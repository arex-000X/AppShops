package com.example.appshops.main.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R
import com.example.appshops.main.adapter.AdapterMessage
import com.example.appshops.main.viewmodel.ChatViewModeFactory
import com.example.appshops.main.viewmodel.ChatViewModel
import com.example.appshops.model.MessageModel

class FragmentChat : Fragment() {

    private lateinit var userNameTitle_chat: TextView
    private lateinit var statUserView: View
    private lateinit var inputSend: EditText
    private lateinit var inputSendViewButton:ImageView
    private lateinit var recylerview: RecyclerView
    private lateinit var viewModelFactory: ChatViewModeFactory
    private lateinit var viewModel: ChatViewModel
    private lateinit var adapterMessage: AdapterMessage
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
        adapterMessage = AdapterMessage(currentId)
        viewModelFactory = ChatViewModeFactory(currentId, currentOthersId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ChatViewModel::class.java)
        recylerview.layoutManager = LinearLayoutManager(context)
        recylerview.adapter = adapterMessage
        observeVieModel()
        inputSendViewButton.setOnClickListener {
            val message = MessageModel(inputSend.text.toString().trim(), currentId, currentOthersId)
            viewModel.sendMessage(message)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }




    fun observeVieModel() {
        viewModel.getMessage().observe(viewLifecycleOwner, Observer {

            adapterMessage.setMessage(it)

        })
        viewModel.error().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                Toast.makeText(context, "Error: ${it}", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.messageSent().observe(viewLifecycleOwner, Observer {
            if (it){
               inputSend.text.clear()
            }
        })
        viewModel.othersUser().observe(viewLifecycleOwner, Observer {
            userNameTitle_chat.text = String.format("%s,%s", it.first_name, it.last_name)
            var isResursOnline = 0
            if (it.isOnline) {
                isResursOnline = R.drawable.circle_green
            } else
                isResursOnline = R.drawable.circle_red


            val drawable: Drawable? = ContextCompat.getDrawable(requireContext(), isResursOnline)
            statUserView.background = drawable
        })
    }
    fun iniViews(view: View) {
        userNameTitle_chat = view.findViewById(R.id.userNameTitle_chats)
        statUserView = view.findViewById(R.id.status_users)
        inputSend = view.findViewById(R.id.inputSend)
        recylerview = view.findViewById(R.id.recyclerViewChats)
        inputSendViewButton = view.findViewById(R.id.sendView)
    }
    companion object {
        var currentId: String = "curent_id"
        var currentOthersId: String = "curentUser_id"
        fun contentFragment(currentId: String, currentOthersId: String) {
            this.currentId = currentId
            this.currentOthersId = currentOthersId


        }
    }
}