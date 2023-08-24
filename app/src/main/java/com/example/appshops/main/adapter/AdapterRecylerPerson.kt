package com.example.appshops.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appshops.R

class AdapterRecylerPerson: RecyclerView.Adapter<AdapterRecylerPerson.ViewHolderPerson>() {

    var listMenu = ArrayList<String>()

    fun menu(listMenu:ArrayList<String>){
        this.listMenu = listMenu
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPerson {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.person_option,parent,false)
        return ViewHolderPerson(view)
    }
    override fun onBindViewHolder(holder: ViewHolderPerson, position: Int) {
        val menu = listMenu.get(position)


    }

    override fun getItemCount() = listMenu.size




    inner class ViewHolderPerson(itemView: View):RecyclerView.ViewHolder(itemView){

    }
}