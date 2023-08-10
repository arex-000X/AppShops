package com.example.appshops.manager

import androidx.fragment.app.Fragment

interface ManagerFragments {
    fun createMainFragment(fragment: Fragment)
    fun addFragment(fragment: Fragment)
    fun addFragmentBackButton(fragment: Fragment)
    fun replaceFragment(fragment: Fragment, addToBackButton: Boolean)
}