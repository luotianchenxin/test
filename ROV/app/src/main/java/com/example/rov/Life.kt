package com.example.rov

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BUTTON_X
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.databinding.DataBindingUtil.*
import com.example.rov.databinding.ActivityLife2Binding
import videoTransmission.H264Player
import videoTransmission.JoyStickClient
import java.net.DatagramPacket

class Life : AppCompatActivity() {
    val joystick_client = JoyStickClient()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLife2Binding? = setContentView(
            this,
            R.layout.activity_life2
        )

        binding?.back?.setOnClickListener {
            finish()
        }

//       val surfaceHolder = binding?.lifeVideo?.holder
//        surfaceHolder?.addCallback(object : SurfaceHolder.Callback {
//            var mH264Player:H264Player? = null
//           override fun surfaceCreated(holder: SurfaceHolder) {
//
//
//                mH264Player = H264Player(surfaceHolder.surface)
//                mH264Player?.startDoing()
//           }
//
//           override fun surfaceChanged(
//               holder: SurfaceHolder,
//                format: Int,
//                width: Int,
//                height: Int
//           ) {
//            }
//
//            override fun surfaceDestroyed(holder: SurfaceHolder) {
//                 mH264Player?.releaseMediaCodec()
//                Log.d("surface_test", "surfaceDestroyed\n")
//            }
//                  })


        binding?.rockingBar?.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN ->{
                    Log.d("RockingBarTest", "X:ACTION_DOWN\n")
                }
                MotionEvent.ACTION_MOVE ->{
                    if(judgeScope(event.x,event.y,binding.rockingBar.width,binding.rockingBar.height)) {
                        Log.d("RockingBarTest", "X:${(event.x-(binding.rockingBar.width/2))/(binding.rockingBar.width/4)}\n")
                        Log.d("RockingBarTest", "Y:${(event.y-(binding.rockingBar.height/2))/(binding.rockingBar.height/4)}\n")
                    }
                }
                MotionEvent.ACTION_UP ->{
                    Log.d("RockingBarTest", "X:ACTION_UP\n")
                }
            }

            return@setOnTouchListener false
        }
    }





    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {

        Thread {

            Log.d("bluetoothTest", "event?.action:${event?.action}\n")
                if (event != null) {
                    if (event.getAxisValue(MotionEvent.AXIS_Z) != 0.0.toFloat()) {
                        Log.d("bluetoothTest", "right_X:${event.getAxisValue(MotionEvent.AXIS_Z)}\n")
                        joystick_client.send_data("right_X:${event.getAxisValue(MotionEvent.AXIS_Z)}")
                    }
                    if (event.getAxisValue(MotionEvent.AXIS_RZ) != 0.0.toFloat()) {
                        Log.d("bluetoothTest", "right_Y:${event.getAxisValue(MotionEvent.AXIS_RZ)}\n")
                        joystick_client.send_data("right_Y:${event.getAxisValue(MotionEvent.AXIS_RZ)}")
                    }
                    if (event.getAxisValue(MotionEvent.AXIS_X) != 0.0.toFloat()) {
                        Log.d("bluetoothTest", "left_X:${event.getAxisValue(MotionEvent.AXIS_X)}\n")
                        joystick_client.send_data("left_X:${event.getAxisValue(MotionEvent.AXIS_X)}")
                    }
                    if (event.getAxisValue(MotionEvent.AXIS_Y) != 0.0.toFloat()) {
                        Log.d("bluetoothTest", "left_Y:${event.getAxisValue(MotionEvent.AXIS_Y)}\n")
                        joystick_client.send_data("left_Y:${event.getAxisValue(MotionEvent.AXIS_Y)}")
                    }
                    if (event.getAxisValue(MotionEvent.AXIS_HAT_Y) == (-1).toFloat()) {
                        Log.d("bluetoothTest", "UP\n")
                        joystick_client.send_data("UP")
                    }
                    if (event.getAxisValue(MotionEvent.AXIS_HAT_Y) == 1.toFloat()) {
                        Log.d("bluetoothTest", "DOWN\n")
                        joystick_client.send_data("DOWN")
                    }
                    if (event.getAxisValue(MotionEvent.AXIS_HAT_X) == (-1).toFloat()) {
                        Log.d("bluetoothTest", "LEFT\n")
                        joystick_client.send_data("LEFT")
                    }
                    if (event.getAxisValue(MotionEvent.AXIS_HAT_X) == 1.toFloat()) {
                        Log.d("bluetoothTest", "RIGHT\n")
                        joystick_client.send_data("RIGHT")
                    }
                }

        }.start()

        return true
    }


    private fun judgeScope(x:Float,y:Float,width:Int,height:Int):Boolean{
        val radius = width.toFloat().coerceAtMost(height.toFloat())/4
        val xMax = width/2 + radius
        val xMin = width/2 - radius
        val yMax = height/2 + radius
        val yMin = height/2 - radius
        return x in xMin..xMax && y in yMin..yMax
    }




    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Thread {
            when (keyCode) {
                // 监控/拦截/屏蔽A方向键
                96 -> {
                    Log.d("bluetoothTest", "A键\n")
                    joystick_client.send_data("A")
                }
                // 监控/拦截/屏蔽B方向键
                97 -> {
                    Log.d("bluetoothTest", "B键\n")
                    joystick_client.send_data("B")
                }
                99 -> {
                    Log.d("bluetoothTest", "X键\n")
                    joystick_client.send_data("X")
                }
                100 -> {
                    Log.d("bluetoothTest", "Y键\n")
                    joystick_client.send_data("Y")
                }
                102->{
                    Log.d("bluetoothTest", "LB\n")
                    joystick_client.send_data("LB")
                }
                103->{
                    Log.d("bluetoothTest", "RB\n")
                    joystick_client.send_data("RB")
                }
                104 ->{
                    Log.d("bluetoothTest", "LT键\n")
                    joystick_client.send_data("LT")
                }
                105 ->{
                    Log.d("bluetoothTest", "RT键\n")
                    joystick_client.send_data("RT")
                }
            }
        }.start()
        return true
    }


}