package com.example.ch2

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Test1_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 화면 구성을 위한 뷰 준비
//        val name = TextView(this).apply {
//            typeface = Typeface.DEFAULT_BOLD // 굵게
//            text = getString(R.string.location_name)
//        }
//
//        // 이미지 출력 뷰
//        val image = ImageView(this).also {
//            // 객체를 람다함수에 매개변수로 ==> it
//            // 리소스 이미지를 획득해서, ImageView에 지정한다.
//            it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lake_1))
//        }
//        val address = TextView(this).apply {
//            typeface = Typeface.DEFAULT_BOLD
//            text = "c"
//        }
//
//        // 여러 뷰를 화면에 출력하고 싶다.
//        // 계층으로 묶여야 한다. 단일 계층으로 묶어서 출력해야 한다.
//        val layout = LinearLayout(this).apply {     // 폴더 역할자..
//            orientation = LinearLayout.VERTICAL     // 나열 방향..
//            gravity = Gravity.CENTER    // 정렬 위치..
//            addView(name, WRAP_CONTENT, WRAP_CONTENT)
//            addView(image, WRAP_CONTENT, WRAP_CONTENT)
//            addView(address, WRAP_CONTENT, WRAP_CONTENT)
//        }
//
//         // 화면 출력. 뷰 계층의 루트 객체를 출력. 하위에 달라 붙은 뷰가 간이 화면에 출력
//         // 화면 구성을 코드에 직접 했다. 이렇게 하면 layout xml 을 만들지 않아도 된다.
//        setContentView(layout)
//
//        ViewCompat.setOnApplyWindowInsetsListener(layout) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // layout xml로 화면 구성
        // 출력할 뷰가 xml 에 명시. 액티비티에서는 어느 xml인지 알려주면 된다.
        // 화면 출력 명령 : xml에 명시된 대로 View 객체를 생성해서 메모리에 올려(inflate) + 출력하라는 의미
        setContentView(R.layout.activity_test11)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}