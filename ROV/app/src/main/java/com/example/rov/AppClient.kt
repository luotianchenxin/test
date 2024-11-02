package com.example.rov


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.database.ServerInfoDatabase
import com.example.database.ServerInformation
import com.example.rov.databinding.ActivityAppClientBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.concurrent.thread


class AppClient : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myServerInfoDao = ServerInfoDatabase.getDatabase(this).serverInfoDao()

        val binding: ActivityAppClientBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_app_client
        )
        Thread {
            val newServerInfo = myServerInfoDao.upDataInfo()
            binding.serverAddress.text = "当前服务器IP地址:${newServerInfo.serverIpAddress}"
            binding.lifePort.text = "当前监控端口:${newServerInfo.liefPort}"
            binding.joystickPort.text = "当前手柄端口:${newServerInfo.joystickPort}"
        }.start()
        binding.back.setOnClickListener {
            finish()
        }
        binding.sendAddress.setOnClickListener {

        }
        binding.sendLifePort.setOnClickListener {

        }
        binding.sendJoystickPort.setOnClickListener {

        }

    }


}




