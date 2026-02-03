package com.example.ch7

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        // 퍼미션을 사용자에게 허락받기 위한 launcher 준비한다.
        // registerForActivityResult 함수를 가지고 런처를 만든다.
        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission() // 퍼미션 다이얼로그를 띄운다.
        ) {// 두번째 매개변수는 람다함수여서
            if (it) {
                // 퍼미션 허락
                val intent = Intent("CH7_ACTION_TWO")
                startActivity(intent)
            } else {
                // 퍼미션 거부
                Toast.makeText(this, "permission denied...", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button2.setOnClickListener {
            // 퍼미션 허락 상태 체크
            if (ContextCompat.checkSelfPermission(this, "com.example.ch7_outer.TWO_PERMISSION") == PackageManager.PERMISSION_GRANTED) {
                // 허락 상태
                // 액티비티 실행
                val intent = Intent("CH7_ACTION_TWO")
                startActivity(intent)
            } else {
                // 거부 상태
                // 퍼미션 조정 다이얼로그를 띄운다. launcher 에게 일을 시키면 된다.
                launcher.launch("com.example.ch7_outer.TWO_PERMISSION")
            }
        }

        binding.button3.setOnClickListener {
            // 액티비티 실행
            val intent = Intent("CH7_ACTION_THREE")
            startActivity(intent)
        }
    }
}