package com.example.appshops.manager

import androidx.fragment.app.Fragment

interface   ManagerFragments {
    fun createMainFragment(fragment: Fragment,container:Int)
    fun addFragment(fragment: Fragment,container:Int)
    fun addFragmentBackButton(fragment: Fragment,container:Int)
    fun replaceFragment(fragment: Fragment, addToBackButton: Boolean,container:Int)
}