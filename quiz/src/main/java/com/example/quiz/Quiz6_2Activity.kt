package com.example.quiz

import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz.databinding.ActivityQuiz62Binding

class Quiz6_2Activity : AppCompatActivity() {
    lateinit var binding : ActivityQuiz62Binding

    inner class WebAppInterface {
        @JavascriptInterface
        fun calcNum(num: Int) {
            Log.d("Quiz6_2Activity", "plusNum() num $num")
            var result = 0
            for (i in 1..num) {
                result += i
            }
            Log.d("Quiz6_2Activity", "plusNum() result $result")
            binding.plusCalcWebview.post {
                binding.plusCalcWebview.loadUrl("javascript:updateText($result)")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityQuiz62Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.plusCalcWebview.settings.javaScriptEnabled = true
        binding.plusCalcWebview.addJavascriptInterface(WebAppInterface(), "CalAndroid")
        binding.plusCalcWebview.loadUrl("file:///android_asset/num_plus.html")
    }
}