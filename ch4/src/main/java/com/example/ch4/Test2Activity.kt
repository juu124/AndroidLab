package com.example.ch4

import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Test2Activity : AppCompatActivity() {

    // 최초의 좌표
    var initX: Float = 0f

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // back button 이벤트 처리
        // onBackPress(){} : api 33에서 deprecated 됐다.
        // onKeyDown() { } : api 36에서 적용되지 않는다.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showToast("back button pressed")
            }
        })
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                initX = event.x
            }

            MotionEvent.ACTION_UP -> {
                val diff = initX - event.rawX
                when {
                    diff >= 30 -> showToast("왼쪽으로 화면을 밀었군요")
                    diff < -30 -> showToast("화면을 오른쪽으로 밀었군요")
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            showToast("볼륨 업")
        }
        return super.onKeyDown(keyCode, event)
    }
}