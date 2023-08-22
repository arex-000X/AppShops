package com.example.appshops

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.appshops.manager.ManagerFragments

class GlobalViewModel(private val fragmentManager: FragmentManager) : ViewModel(),
    ManagerFragments {




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
        fragmentManager.beginTransaction()
            .addToBackStack(fragment::class.java.simpleName)
            .replace(container, fragment)
            .commit()
    }

    override fun replaceFragment(fragment: Fragment, addToBackButton: Boolean, container: Int) {
        when (addToBackButton) {
            true -> addFragmentBackButton(fragment, container)
            false -> addFragment(fragment, container)

        }

    }

    override fun remove(fragment: Fragment) {
        fragmentManager.beginTransaction().remove(fragment)
    }


}