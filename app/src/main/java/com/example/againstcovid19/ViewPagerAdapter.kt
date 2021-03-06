package com.example.againstcovid19

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
        private val JUMLAH_MENU = 6
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> { return HomeFragment() }
                1 -> { return ListDataFragment() }
                2 -> { return NewsFragment() }
                3 -> { return NewsPortalFragment() }
                4 -> { return InfoFragment() }
                5 -> { return ProfileFragment() }
                else -> {
                    return ListDataFragment()
                }
            }
        }
        override fun getItemCount(): Int {
            return JUMLAH_MENU
        }
}
