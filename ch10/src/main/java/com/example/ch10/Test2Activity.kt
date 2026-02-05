package com.example.ch10

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch10.databinding.ActivityTest2Binding

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.insertButton.setOnClickListener {
            // 유저 입력 데이터를 추출하여 저장해보자
            // 일단 유저의 입력 데이터를 추출한다.
            val name = binding.nameInput.text.toString()
            val address = binding.addressInput.text.toString()
            val age = binding.ageInput.text.toString().toInt()

            // db 저장
            val db = DBHelper(this).writableDatabase    // 파일에 접근하는 것이기 때문에 writableDatabase 사용
            val values = ContentValues()    // insert, update 될 데이터를 저장하는 일종의 Map 객체라고 생각하면 된다.
            // key 만 column명으로 하면된다.
            values.put("name", name)
            values.put("address", address)
            values.put("age", age)
            db.insert("tb_user", null, values)  // db에 저장한다.
            db.close() // db를 닫는 이유는 메모리 누수를 방지하기 위해서이다.

            // 위의 코드는 아래처럼도 사용할 수 있다.
//            db.execSQL("INSERT INTO tb_user(name, address, age) VALUES(?, ?, ?)", arrayOf(name, address, age))

            Toast.makeText(this, "db 저장이 성공했습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.queryButton.setOnClickListener {
            val db = DBHelper(this).readableDatabase    // 값을 가져오는 작업만 하기 때문에 readableDatabase 사용
            val cursor = db.query("tb_user", null, null, null, null, null, null)

            //위의 로직은 아래의 구문과 같다
//            db.rawQuery("SELECT * FROM tb_user",    NULL)

            var result = ""
            while (cursor.moveToNext()) {
                result += cursor.getString(1)
                result += " : "
                result += cursor.getString(2)
                result += " : "
                result += cursor.getString(3)
                result += "\n"
            }
            binding.resultView.text = result
            db.close()
        }


    }
}