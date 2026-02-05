package com.example.ch9

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch9.databinding.ActivitySomeBinding

class SomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivitySomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 자신을 실행시킨 intent 에서 데이터를 추출한다.
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0)
        binding.dataView.text = "$data1, $data2"

        binding.button.setOnClickListener {
            // 데이터를 포함해서 자신을 종료시켜 자동으로 이전화면으로 넘어가게 한다.
            // 이때 결과 데이터도 넘기겠다.
            intent.putExtra("result", "hello world")
            // 되돌리는 상태를 표시해야한다. 어떤 상태로 되돌리는 지 표시해야한다.
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}