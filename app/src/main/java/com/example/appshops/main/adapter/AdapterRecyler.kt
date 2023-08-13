package com.example.appshops.main.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R
import com.example.appshops.model.User


class AdapterRecyler : RecyclerView.Adapter<AdapterRecyler.ViewHolderMassager>() {


    private var user: List<User> = ArrayList()




    @SuppressLint("NotifyDataSetChanged")
    fun setUser(user: List<User>) {
        this.user = user
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMassager {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item_menu, parent, false)

        return ViewHolderMassager(view)
    }


    override fun onBindViewHolder(holder: ViewHolderMassager, position: Int) {


        val user: User = user.get(position)
        val userInfo = String.format("%s,%s", user.first_name, user.last_name)
        holder.userInfoView.text = userInfo
        var isResursOnline = 0
        if (user.isOnline) {
            isResursOnline = R.drawable.circle_green
        } else
            isResursOnline = R.drawable.circle_red


        val drawable: Drawable? = ContextCompat.getDrawable(holder.itemView.context, isResursOnline)
        holder.statusUser.background = drawable

    }


    override fun getItemCount(): Int = user.size


    interface OnClicklistener{
        fun onUserClickIte(user:User)
    }



    inner class ViewHolderMassager(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userInfoView: TextView
        var statusUser: TextView

        init {
            userInfoView = itemView.findViewById(R.id.userNameTitle_chat)
            statusUser = itemView.findViewById(R.id.status_user)
        }

    }
}