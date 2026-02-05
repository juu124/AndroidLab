package com.example.ch9

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch9.databinding.ActivityTest2Binding

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

        // intent가 되돌아 왔을때 사후 처리를 하는 케이스
        // launcher방법을 사용
        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // callback 되돌아 왔을대 자동 실행된다.
            // 결과 데이터 획득
            val intent = it.data
            binding.dataView.text = intent?.getStringExtra("result")
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, SomeActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 100)
            // intent를 실행시키는 방법이 startActivity말고 launch도 있다.
            // 이렇게 하면 사후 결과 데이터를 획득할 수도 있음
            launcher.launch(intent)
        }
    }
}