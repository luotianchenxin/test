package com.example.rov

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.rov.databinding.FragmentBluetoothBinding


class Bluetooth : Fragment() {
    private var mbinding: FragmentBluetoothBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bluetooth,
            container, false)
        // Inflate the layout for this fragment
        mbinding?.receiver?.setOnClickListener {
            val intent = Intent(context, AppClient::class.java)
            startActivity(intent)
        }
        mbinding?.command?.setOnClickListener {
            val intent = Intent(context, ServerCommand::class.java)
            startActivity(intent)
        }
        return mbinding!!.root
    }


}