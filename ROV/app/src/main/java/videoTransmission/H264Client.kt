package videoTransmission

import android.util.Log
import java.io.File
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException

class H264Client{
    private val address: InetAddress = InetAddress.getByName("192.168.1.222")
    private val port = 9000
    private val UDPSocket = DatagramSocket()
    var fameList:ArrayList<ByteArray> = ArrayList()
    var finallyList:ArrayList<ByteArray> = ArrayList()

    fun deleteFirstFrame(){
        finallyList.removeFirst()
    }

    fun closeClient(){
        val overSignal = DatagramPacket(
            "close".toByteArray(),
            "close".toByteArray().size, address, port
        )
        Thread{
            UDPSocket.send(overSignal)
            UDPSocket.close()
        }.start()

    }



        private fun sendData(socket: DatagramSocket) {

        val startSignal = DatagramPacket(
            "start".toByteArray(),
            "start".toByteArray().size, address, port
        )

        socket.send(startSignal)
            Log.d("UDP_test","socket send\n")
    }


        private fun receiveData(socket: DatagramSocket) {

            val data = ByteArray(1200)
            val packet = DatagramPacket(data, data.size)
            var count =0
            val overSignal = "over"
            val reserveArray = ByteArray(5000000)
            while (true){
                try {
                    socket.receive(packet)
                    Log.d("UDP_test","packet.length${packet.length}\n")
                }catch (e: SocketException){
                    Log.d("UDP_test","Socket closed\n")
                    break
                }
                if(packet.length==4){
                    Log.d("UDP_test","count:${count}\n")
                    val oneFrame =ByteArray(count)
                    System.arraycopy(reserveArray,0,oneFrame,0,count)

                    finallyList.add(oneFrame)

                    count =0
                }else{
                    System.arraycopy(packet.data, 0, reserveArray, count, packet.length)
                    count += packet.length
                }
            }
    }

    fun startReceiveData(){
        Thread {
            sendData(UDPSocket)
            receiveData(UDPSocket)
        }.start()
    }
}