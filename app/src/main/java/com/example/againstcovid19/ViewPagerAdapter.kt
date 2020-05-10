package com.example.againstcovid19

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
        private val JUMLAH_MENU = 3
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> { return HomeFragment() }
                1 -> { return DataFragment() }
                2 -> { return ProfileFragment() }
                else -> {
                    return DataFragment()
                }
            }
        }
        override fun getItemCount(): Int {
            return JUMLAH_MENU
        }
}
