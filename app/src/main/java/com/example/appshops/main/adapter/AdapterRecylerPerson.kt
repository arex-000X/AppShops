package com.example.appshops.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R

class AdapterRecylerPerson : RecyclerView.Adapter<AdapterRecylerPerson.ViewHolderPerson>() {

    var listMenu = ArrayList<String>()

    private  lateinit var  onClickListenerItem:OnClickListenerItem

    fun setOnClickListenerItem( onClickListenerItem:OnClickListenerItem){
        this.onClickListenerItem = onClickListenerItem
    }

    fun menu(listMenu: ArrayList<String>) {
        this.listMenu = listMenu
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPerson {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.person_option, parent, false)
        return ViewHolderPerson(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPerson, position: Int) {
        val menu = listMenu.get(position)
        holder.textItem.text = menu
        when (holder.textItem.text) {
            "Shopping cart" -> holder.textItem
                .setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_shopping_cart_24,
                    0,
                    R.drawable.baseline_arrow_forward_ios_24,
                    0
                )

            "Setting" -> holder.textItem
                .setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_settings_24,
                    0,
                    R.drawable.baseline_arrow_forward_ios_24,
                    0
                )

            "Help" -> holder.textItem
                .setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_help_outline_24,
                    0,
                    R.drawable.baseline_arrow_forward_ios_24,
                    0
                )

            "Log out" -> holder.textItem
                .setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.baseline_logout_24,
                    0,
                    R.drawable.baseline_arrow_forward_ios_24,
                    0
                )


        }


        holder.itemView.setOnClickListener {
            if (onClickListenerItem != null) {
                onClickListenerItem.onClick(position)
            }
        }


    }

    override fun getItemCount() = listMenu.size


    interface OnClickListenerItem{
        fun onClick(position: Int)
    }

    inner class ViewHolderPerson(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textItem: TextView

        init {
            textItem = itemView.findViewById(R.id.item_menu)
        }


    }
}