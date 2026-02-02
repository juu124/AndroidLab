package com.example.ch5

import android.os.Bundle
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch5.databinding.ActivityTest3Binding

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

        // 코드에서 사이즈 지정의 기본
        // 1. 코드에서는 단위를 추가할 수 없다. px로 적용된다.
        // 2. 본 사이즈 호환성을 위한다면, 앱이 실행되는 디바이스의 density(어떤 폰인지)를 얻어 직접 계산(논리적 단위 어떤 것인지 :xxxhdpi)으로 적용해야한다.
        // 3. dimens.xml 에 사용하고자 하는 사이즈를 등록하면서, 논리적 단위를 입력해야한다.
        // 코드에서 dimens.xml 에 등록한 사이즈 값을 획득할 수 있다. px로 나오지만  density에 따라 계산되어서 나온다.

        // 직접 숫자값으로 줌
        // TextView에 한해서. TextView의 문자열 크기에 한해서
        // 코드에서 사이즈 지정이 기본 sp로 등록되게 되어 있다.
        binding.text1.setTextSize(15f)

        // density알아내서 줌
        val density = resources.displayMetrics.density  // 1.0, 2.0 이렇게 넘어온다. 논리적 단위(hdpi 배율)
        binding.text2.setTextSize(15f * density)

        // 리소스로 등록한 demens 를 사용
        val size = resources.getDimension(R.dimen.test_size)
        binding.text3.setTextSize(size)

        // TextView의 문자열 사이즈 지정할때 기본 sp가 지정되지 않고 px로 지정하고 싶다면?
        binding.text4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 15f)

        binding.button1.layoutParams.width = 100            // 픽셀
        binding.button2.layoutParams.width = (100 * density).toInt()    // density를 곱한 값
    }
}