package com.darwish.roomie.presentation.group.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.darwish.roomie.presentation.group.currentgroups.CurrentGroupsFragment
import com.darwish.roomie.presentation.group.pendinggroups.PendingGroupsFragment

class GroupPagerAdaptor(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {

        return if(position == 0) {
            CurrentGroupsFragment()
        } else {
            PendingGroupsFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if(position == 0) {
            "Current Groups"
        } else {
            "Pending Groups"
        }
    }
}