package com.it.userdirectory.presentation.navigation

import androidx.fragment.app.FragmentActivity
import com.it.userdirectory.R
import com.it.userdirectory.presentation.fragment.DetailFragment
import com.it.userdirectory.presentation.fragment.ListFragment

class ScreenNavigation {
    companion object{
        fun listScreen(activity: FragmentActivity){
            activity
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                .replace(R.id.fragment_container_main, ListFragment())
                .commit()
        }

        fun detailScreen(activity: FragmentActivity){
            activity
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                .addToBackStack(null).replace(R.id.fragment_container_main, DetailFragment())
                .commit()

        }
    }
}