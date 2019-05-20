package com.darwish.roomie.presentation.group

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.darwish.roomie.R
import com.darwish.roomie.presentation.group.common.GroupPagerAdaptor
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {

    private lateinit var groupPager: GroupPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        groupPager = GroupPagerAdaptor(
            supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        viewPager.adapter = groupPager

        tabLayout.setupWithViewPager(viewPager)
    }
}
