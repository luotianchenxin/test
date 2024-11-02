package com.example.socketServer

import android.util.Log
import android.widget.ArrayAdapter
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class CommandClient {

    private val address: InetAddress = InetAddress.getByName("192.168.1.222")
    private val port = 7000
    val UDPSocket = DatagramSocket()


    fun sendData( UDPSocket:DatagramSocket,message:String){
        val startSignal = DatagramPacket(
            message.toByteArray(),
            message.toByteArray().size, address, port
        )

       UDPSocket.send(startSignal)
    }

    fun receiveData(UDPSocekt:DatagramSocket, adapter:ArrayAdapter<String>,dataList: ArrayList<String>) {
        val data = ByteArray(1200)
        val packet = DatagramPacket(data, data.size)
        Thread {
            while (true) {
                UDPSocket.receive(packet)
                Log.d("server_command", "receive:${data}")
                dataList.add(data.toString())
                adapter.notifyDataSetChanged()
            }
        }.start()
    }
}