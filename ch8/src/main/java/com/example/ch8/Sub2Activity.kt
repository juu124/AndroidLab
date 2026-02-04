package com.example.ch8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8.databinding.ActivitySub1Binding
import com.example.ch8.databinding.ActivitySub2Binding

class Sub2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivitySub2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 테마 설정을 통해 ActionBar가 나오지 않게 하고,
        // 개발자가 직접 준비하는 Toolbar로 ActionBar를 대체하겠다.
        // ActionBar 내용이 개발자 뷰인 Toolbar에 적용되어야 한다. 어느 뷰인지 알려줘야한다.(아래 코드 setSupportActionBar)
        // ActionBar를 toolbar로 대체하겠어요~ 따잇!
        setSupportActionBar(binding.toolbar)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}