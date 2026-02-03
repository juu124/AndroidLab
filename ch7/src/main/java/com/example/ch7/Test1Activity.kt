package com.example.ch7

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch7.databinding.ActivityTest1Binding

class Test1Activity : AppCompatActivity() {
    lateinit var binding: ActivityTest1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button1.setOnClickListener {
            // 액티비티 실행
            val intent = Intent("CH7_ACTION_ONE")
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            // 액티비티 실행
            val intent = Intent("CH7_ACTION_TWO")
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            // 액티비티 실행
            val intent = Intent("CH7_ACTION_THREE")
            startActivity(intent)
        }
    }
}