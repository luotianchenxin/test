package com.example.rov

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.InputDevice
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rov.databinding.ActivityMainBinding
import com.example.socketServer.CommandClient
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int { return 2 }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> Home()
                    else -> Bluetooth()
                }
            }
        }
        TabLayoutMediator(binding.tabLayout,binding.viewPager2){
                tab, position ->
            when(position){
                0->initHomeUI(tab)
                else -> initDeviceUI(tab)
            }
        }.attach()

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initHomeUI(tab:TabLayout.Tab){
        tab.icon = getDrawable(R.drawable.ic_icon_home)
        tab.text = "首页"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initDeviceUI(tab:TabLayout.Tab){
        tab.icon = getDrawable(R.drawable.ic_icon_device)
        tab.text = "设备"
    }
}