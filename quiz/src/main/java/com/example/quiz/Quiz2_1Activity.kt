package com.example.quiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.databinding.ActivityQuiz21Binding

class Quiz2_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityQuiz21Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = binding.etName.text
        val email = binding.etEmail.text

        binding.btnJoin.setOnClickListener {
            if (name.isEmpty()) {
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (!binding.checkAgree.isChecked) {
                Toast.makeText(this, "이용약관에 동의해주세요", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvResultTitle.visibility = View.VISIBLE
                binding.tvResult.visibility = View.VISIBLE

                val gender = if (binding.rbMale.isChecked) "남성" else "여성"
                val result = """
                    이름 : $name
                    이메일 : $email
                    성별 : $gender
                    """.trimIndent()
                binding.tvResult.text = result

                // 입력창 초기화
                binding.etName.text.clear()
                binding.etEmail.text.clear()
                binding.rbMale.isChecked = true
                binding.checkAgree.isChecked = false
            }
        }
    }
}