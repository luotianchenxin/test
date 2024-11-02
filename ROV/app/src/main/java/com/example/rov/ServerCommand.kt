package com.example.rov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.rov.databinding.ActivityAppClientBinding
import com.example.rov.databinding.ActivityServerCommandBinding
import com.example.socketServer.CommandClient

class ServerCommand : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityServerCommandBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_server_command
        )



        val dataList: ArrayList<String> = ArrayList()
        val adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList)
        binding.listview.adapter=adapter
        val myServerCommand = CommandClient()
        myServerCommand.receiveData(myServerCommand.UDPSocket,adapter,dataList)

        binding.back.setOnClickListener {
            finish()
        }
        binding.sendData.setOnClickListener {

            if(binding.inputText.text.isNotEmpty()){
                val sendMessage = binding.inputText.text.toString()
                dataList.add(sendMessage)
                adapter.notifyDataSetChanged()
                Thread{
                    myServerCommand.sendData(myServerCommand.UDPSocket, sendMessage)
                }.start()
                Log.d("server_command","message:$sendMessage\n")
                binding.inputText.setText("")
            }
        }
    }

}