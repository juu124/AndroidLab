package com.example.ch6

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch6.databinding.ActivityTest2Binding

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 배열 리소스를 획득한다.
        val datas = resources.getStringArray(R.array.spinner_array) // array의 String type
        // 항목 하나에 문자열 데이터 하나, 순차적으로 출력시키는 adapter이다.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, datas)  // 항목 layout xml (android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        // 우리가 필터링 로직을 짤 필요없다. 입력한 글을 이용해 추천단어를 필터랑 해준다.
        // 유저가 글 입력하는 순간. 그 입력한 글을 이용해 추천단어를 필터링 한다. 개발자가 필터링 할 필요는 없다는 말이다. 내부적으로는 해준다.
        val autoDatas = resources.getStringArray(R.array.auto_array)
        val autoAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, autoDatas)
        binding.auto.setAdapter(autoAdapter)
    }
}