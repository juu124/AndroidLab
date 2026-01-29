package com.example.ch2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.databinding.ActivityTest3Binding

class Test3Activity : AppCompatActivity() {
    lateinit var visibleBtn: Button
    lateinit var targetView: TextView
    lateinit var invisibleBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        // inflate + 출력 => view 객체 생성 완료
//        setContentView(R.layout.activity_test3)
//        // 필요한 뷰 객체 획득.
//        visibleBtn = findViewById(R.id.visibleBtn)
//        targetView = findViewById(R.id.targetView)
//        invisibleBtn = findViewById(R.id.invisibleBtn)
//
//        // 획득한 뷰 객체에 이벤트를 등록한다.
//        visibleBtn.setOnClickListener {
//            targetView.visibility = View.VISIBLE
//        }
//        invisibleBtn.setOnClickListener {
//            targetView.visibility = View.INVISIBLE
//        }

        // ViewBinding 기법을 사용하면, 자동으로 만들어지는 XXXBinding 클래스에 infloast명령은 내려야한다.
        // 클래스는 만들어주지만, 해당 객체에 관한 메모리를 올려주진 않는다.
        // 미리미리 올릴 필요 없이 명령에 따라 메모리에 올린다.
        val binding = ActivityTest3Binding.inflate(layoutInflater)
        // 화면 출력. binding 너네가 가지고 있는 객체 중 root 객체를 출력해
        setContentView(binding.root)

        binding.visibleBtn.setOnClickListener {
            binding.targetView.visibility = View.VISIBLE
        }
        binding.invisibleBtn.setOnClickListener {
            binding.targetView.visibility = View.INVISIBLE
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}