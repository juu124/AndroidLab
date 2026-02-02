package com.example.ch6

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch6.databinding.ActivityTest3Binding

class Test3Activity : AppCompatActivity() {
    lateinit var binding: ActivityTest3Binding

    // js에게 공개하기 위한 클래스. 이 크래스 객체 함수를 호출하게 하려고
    inner class WebAppInterface {
        // 클래스 객체가 js에 공개되었다고 하더라도 아래의 어노테이션이 추가된 함수에 한해서만 호출이 가능하다.
        // 함수별로 오픈하고 싶은 함수가 있고 하기 싫은 함수가 있다. 어노테이션이 추가되야 js가 호출할 수 있다.
        @JavascriptInterface
        fun updateTextView(text: String) {
            binding.textView.text = text    // js에서 전달한 데이터를 화면에 출력. 어노테이션이 있기 때문에 가능함
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // webView js engine enable 기본이 disable이다. 그래서 js코드가 실행되게 하려면 바꿔야한다.
        binding.webView.settings.javaScriptEnabled = true   // 자바스크립트를 켰음

        // js에 코틀린 객체를 공개한다.
        // "Android"는 개발자 임의 단어이다. 공개한 객체를 js에서 이용할 때 객체명이 된다.
        binding.webView.addJavascriptInterface(WebAppInterface(), "Android")

        // 브라우저 이벤트
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,   // alert 의 문자열
                result: JsResult?   // alert 창 자체 객체
            ): Boolean {
                // alert 차으이 문자열을 toast로 대체한다
                Toast.makeText(this@Test3Activity, message, Toast.LENGTH_LONG).show()
                // 코드적으로 alert창을 닫아서 못뜨게..
                result?.confirm()
                return true
            }
        }

        binding.button.setOnClickListener {
            // js function 호출..
            binding.webView.loadUrl("javascript:updateText('Android data')")
        }

        // html 로딩..
        binding.webView.loadUrl("file:///android_asset/test.html")
    }
}