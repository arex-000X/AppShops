package com.example.appshops

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.appshops.manager.ManagerFragments

class GlobalViewModel(private val fragmentManager: FragmentManager) : ViewModel(),
    ManagerFragments {



    fun setfragmentList(array:ArrayList<Int>){
        Log.d("FragmentsList","Fragments ${array.size}")
    }





    override fun createMainFragment(fragment: Fragment, container: Int) {
        val current = fragmentManager.findFragmentById(container)
        if (current == null) {
            fragmentManager.beginTransaction()
                .add(container, fragment).commit()
        }
    }

    override fun addFragment(fragment: Fragment, container: Int) {
        fragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
    }

    override fun addFragmentBackButton(fragment: Fragment, container: Int) {
        var count = 0
        fragmentManager.beginTransaction()
            .addToBackStack("FragmentStack ${count++}")
            .replace(container, fragment)
            .commit()
    }

    override fun replaceFragment(fragment: Fragment, addToBackButton: Boolean, container: Int) {
        when (addToBackButton) {
            true -> addFragmentBackButton(fragment, container)
            false -> addFragment(fragment, container)

        }

    }


}