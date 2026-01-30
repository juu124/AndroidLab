package com.example.ch4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch4.databinding.ActivityTest1Binding

class Test1Activity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    lateinit var binding: ActivityTest1Binding

    fun showToast(message: String) {
        // 토스트 출력하는 개발자 함수
        // 화면에 잠깐 나오다가 자동으로 사라지는 문자열 토스트
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // 두번째 매개변수가 체크 상태값
    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        showToast("check1 is $isChecked")
    }

    // 버튼클릭시 핸들러로 사용할 것임
    // 이벤트 내용이 많다면, 별도의 이벤트 처리 핸들러 클래스를 선언한다.
    inner class EventHandler : View.OnClickListener {
        // 매개변수(v: View?)는 현재 이벤트가 발생한 객체이다.
        override fun onClick(v: View?) {
            when (v) {
                binding.button1 -> showToast("button1 clicked")
                binding.button2 -> showToast("button2 clicked")
            }
        }
    }

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
        binding.button1.setOnClickListener(EventHandler())
//        binding.button2.setOnClickListener(EventHandler())
         binding.button2.setOnClickListener {
             showToast("button2 clicked")
         }


        binding.check1.setOnCheckedChangeListener(this)

        // 이벤트 핸들러가 준비되어야한다.
        // 이 이벤트에서만 사용하는 핸들러라면? => 익명 클래스를 사용한다. (activity에 하거나, inner클래스처럼 사용하는 방법도 있지만..)
        // 매개변수에 들어가는 인터페이스를 구현해야한다. 인터페이스는 타입으로 사용할 수 있기에 아래처럼 사용가능하다
//        binding.check2.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
//            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
//                Log.d("lee", "check2 is $isChecked")
//            }
//        })

        // 인터페이스를 구현한 익명 클래스를 만든다면, 위처럼 object: Interface { 추상 함수 } 형태가 기본이지만,
        // 줄여서 추상함수 안쪽 내용만 { } 로 작성이 가능하다.
        // Single Abstract Method 기법이라고 부른다. 줄여서 SAM 기법
        // 추상함수 하나만 가지는 인터페이스만 가능하다
//        binding.check2.setOnCheckedChangeListener({buttonView, isChecked ->
//            Log.d("lee", "check2 is $isChecked")
//        })

        // 고차함수(hof) 마지막 매개변수가 람다함수라면 () 밖에 사용이 가능하다.
        binding.check2.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("lee", "check2 is $isChecked")
        }
    }
}