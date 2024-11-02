package videoTransmission

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class  H264Service : Service() {

    private val mBinder = TransferBinder()


    class TransferBinder : Binder() {
        private val address: InetAddress = InetAddress.getByName("192.168.0.102")
        private val port = 8800
        private val UDPsocket = DatagramSocket()

        fun sendData(socket: DatagramSocket) {

            val startSignal = DatagramPacket(
                "start".toByteArray(),
                "start".toByteArray().size, address, port
            )

            socket.send(startSignal)
        }
        fun receiveData(socket: DatagramSocket) {

            val data = ByteArray(1024)
            var count = 0
            val packet = DatagramPacket(data, data.size)
            socket.receive(packet)
            // Log.d("testUdp","packet.length is ${String(data, 0, packet.length).toInt()}")
            val allData = ByteArray(String(data, 0, packet.length).toInt())
            while (count < allData.size){
                socket.receive(packet)
                System.arraycopy(data, 0, allData, count, packet.length)
                count += packet.length
            }
            // Log.d("testUdp",allData.contentToString())
        }
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "onCreate executed")
    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("MyService", "onStartCommand executed")
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy executed")
    }
}