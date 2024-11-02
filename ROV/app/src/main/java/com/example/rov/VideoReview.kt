package com.example.rov

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil
import com.example.rov.databinding.ActivityAppClientBinding
import com.example.rov.databinding.ActivityVideoReviewBinding

class VideoReview : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityVideoReviewBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_video_review
        )

        binding.rockingBar.setOnTouchListener { v, event ->
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

    private fun judgeScope(x:Float,y:Float,width:Int,height:Int):Boolean{
        val radius = width.toFloat().coerceAtMost(height.toFloat())/4
        val xMax = width/2 + radius
        val xMin = width/2 - radius
        val yMax = height/2 + radius
        val yMin = height/2 - radius
        return x in xMin..xMax && y in yMin..yMax
    }
}

