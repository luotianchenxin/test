package com.example.rov

import android.content.Intent
import videoTransmission.H264Player
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.rov.databinding.FragmentHomeBinding
import java.io.File
import android.view.InputDevice
import android.widget.Toast


class Home : Fragment() {
    private var mbinding: FragmentHomeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            mbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
            container, false)

           mbinding?.life?.setOnClickListener {
               val intent = Intent(context, Life::class.java)
               startActivity(intent)
           }

        mbinding?.video?.setOnClickListener {
//            Toast.makeText(activity, "敬请期待", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, VideoReview::class.java)
            startActivity(intent)
        }

        mbinding?.analyse?.setOnClickListener {
            val intent = Intent(context, DataAnalysis::class.java)
            startActivity(intent)
        }
        mbinding?.help?.setOnClickListener {
            Toast.makeText(activity, "敬请期待", Toast.LENGTH_SHORT).show()
        }

        return mbinding!!.root
    }


}