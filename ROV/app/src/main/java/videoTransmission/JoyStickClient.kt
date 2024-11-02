package videoTransmission

import android.util.Log
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class JoyStickClient {
    private val address: InetAddress = InetAddress.getByName("192.168.1.222")
    private val port = 8000
    private val UDPSocket = DatagramSocket()

    fun send_data(message:String){
        val startSignal = DatagramPacket(
            message.toByteArray(),
            message.toByteArray().size, address, port
        )

        UDPSocket.send(startSignal)
    }

}