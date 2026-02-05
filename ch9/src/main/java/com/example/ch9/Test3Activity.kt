package com.example.ch9

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch9.databinding.ActivityTest3Binding

class Test3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 퍼미션 조정 다이얼로그 띄우기
        val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission(), {
            if (it) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"))
                startActivity(intent)
            } else {
                Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show()
            }
        })

        binding.button1.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    "android.permission.CALL_PHONE"
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"))
                startActivity(intent)
            } else {
                launcher.launch("android.permission.CALL_PHONE")
            }
        }

        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.45, 126.97"))
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"))
            startActivity(intent)
        }
    }
}