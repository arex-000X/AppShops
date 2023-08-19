package com.example.appshops.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R
import com.example.appshops.model.MessageModel


const val VIEW_MY_CHAT = 100
const val VIEW_OTHER_CHAT = 101

class AdapterMessage(private val currentResUserId: String) :
    RecyclerView.Adapter<AdapterMessage.MessageViewHolder>() {
    var listMessageModel: List<MessageModel> = ArrayList()
    fun setMessage(list: List<MessageModel>) {
        listMessageModel = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        var currentResId = 0
        when (viewType) {
            VIEW_MY_CHAT -> currentResId = R.layout.chat_item
            VIEW_OTHER_CHAT -> currentResId = R.layout.chat_item_others
        }
        val view = LayoutInflater.from(parent.context).inflate(currentResId, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val messageModel: MessageModel = listMessageModel.get(position)
        holder.textMessage.text = messageModel.text
    }

    override fun getItemViewType(position: Int): Int {
        val messageModel: MessageModel = listMessageModel.get(position)
        if (messageModel.sendId == currentResUserId) {
            return VIEW_MY_CHAT
        } else {
            return VIEW_OTHER_CHAT
        }

    }

    override fun getItemCount() = listMessageModel.size


    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textMessage: TextView

        init {
            textMessage = itemView.findViewById(R.id.textMessageChat)
        }

    }

}