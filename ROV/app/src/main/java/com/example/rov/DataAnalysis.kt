package com.example.rov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.rov.databinding.ActivityDataAnalysisBinding
import com.example.rov.databinding.ActivityLife2Binding

class DataAnalysis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityDataAnalysisBinding? = DataBindingUtil.setContentView(
            this,
            R.layout.activity_data_analysis
        )

        binding?.back?.setOnClickListener {
            finish()
        }
    }
}