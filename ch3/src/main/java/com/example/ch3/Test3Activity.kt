package com.example.ch3

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.databinding.ActivityTest3Binding

class Test3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 메모리에 올라감 아직 화면에는 안 올라온 상태
        val binding = ActivityTest3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 이렇게 하면 binding.tab1 이렇게 사용 안해도 된다.
        binding.run {
            tab1.setOnClickListener {
                tabContent1.visibility = View.VISIBLE
                tabContent2.visibility = View.INVISIBLE
                tabContent3.visibility = View.INVISIBLE
            }
            tab2.setOnClickListener {
                tabContent1.visibility = View.INVISIBLE
                tabContent2.visibility = View.VISIBLE
                tabContent3.visibility = View.INVISIBLE
            }
            tab3.setOnClickListener {
                tabContent1.visibility = View.INVISIBLE
                tabContent2.visibility = View.INVISIBLE
                tabContent3.visibility = View.VISIBLE
            }
        }
    }
}