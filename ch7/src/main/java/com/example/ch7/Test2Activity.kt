package com.example.ch7

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch7.databinding.ActivityTest2Binding

class Test2Activity : AppCompatActivity() {
    lateinit var binding: ActivityTest2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toastButton.setOnClickListener {
            val toast = Toast.makeText(this, "toast", Toast.LENGTH_SHORT)
            // toast callback 등록할 수 있다.
            // api 30에서 추가되었다.
            // Build.VERSION.SDK_INT : 우리 앱이 실행되는 유저 폰의 api level
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                toast.addCallback(object : Toast.Callback() {
                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("jay", "toast shown")
                    }

                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("jay", "toast hidden")
                    }
                })
            }
            toast.show()
        }

        // dialog의 button click 이벤트
        // 이벤트 핸들러...
        // dialog : 현재 이벤트가 발생한 dialog 객체. 어떤 다이얼로그인지 알아야한다.
        // which : 버튼의 종류
        val dialogHandler = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Toast.makeText(this, "positive", Toast.LENGTH_SHORT).show()
                }

                DialogInterface.BUTTON_NEGATIVE -> {
                    Toast.makeText(this, "negative", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 각 버튼에 맞는 이벤트 동록
        binding.alertButton.setOnClickListener {
            // AlertDialog는 직접 생성하지 않고, Builder 에게 setter함수로 다이어로그 구성을 명시하면 만들어준다.
            val builder = AlertDialog.Builder(this)
            builder.run {
                setTitle("alert dialog")
                setMessage("hello world")
                // 다이얼로그 버튼을 추가하기 위해서 동일함수 반복 이용 => 중복된다. => 최대 3개 까지만 버튼이 노출된다.
                setPositiveButton("YES", dialogHandler)
                setPositiveButton("YES1", dialogHandler)    // 화면에는 YES1가 노출된다.
                setNeutralButton("NO", dialogHandler)
                show()  // 뜨는 순간
            }
        }

        // 목록 다이얼로그 만들기
        val arrays = resources.getStringArray(R.array.list)

        binding.listButton.setOnClickListener {
            // 목록 다이얼로그
            val builder = AlertDialog.Builder(this)
            builder.run {
                setTitle("list dialog")
                // 두번째 다이얼로그는 함수다.
                setItems(R.array.list) { dialog, which ->   // 항목 선택 이벤트 which : 선택 항목 index가 된다. 몇번째 항목이 눌렀는지 알려준다.
                    Toast.makeText(this@Test2Activity, arrays[which], Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }

        val selectedArray = BooleanArray(arrays.size)
        binding.checkButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.run {
                setTitle("항목 선택")
                // (첫번째 매개변수)arrays : 항목 구성 문자열
                // (두 번째 매개변수)selectedArray : 초기부터 선택되어 있는지의 값
                // dialog, which, isChecked : 이벤트 콜백
                setMultiChoiceItems(arrays, selectedArray, {dialog, which, isChecked ->
                    // 항목의 체크박스를 선택할 때마다 호출된다.
                    // 첫번째꺼 체크박스 함 -> 호출됨
                    // 두번째꺼 체크박스 체크함 -> 호출됨
                    // 체크 할 때마다 해당 함수가 호출된다.
                    selectedArray[which] = isChecked
                })
                setPositiveButton("확인") { dialog, which -> // 이벤트 콜백
                    val selectedOptions = mutableListOf<String>()
                    for (i in selectedArray.indices) {
                        if (selectedArray[i]) {
                            selectedOptions.add(arrays[i])
                        }
                    }
                    Toast.makeText(this@Test2Activity, "선택된 항목 : ${selectedOptions}", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
    }
}