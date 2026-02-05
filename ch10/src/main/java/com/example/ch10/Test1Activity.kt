package com.example.ch10

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SQLiteDatabase 객체를 획득한다. 그래야 내장 DB 사용 가능
        // testdb - db file, 한 파일에 여러 테이블 가능하다
        val db = openOrCreateDatabase("testdb", MODE_PRIVATE, null)

        // 테이블을 먼저 만들어야한다.
        // 그런데 이미 존재하는 테이블을 만들면 에러가 난다. (A 테이블을 만든다고 가정. 한번더 앱을 실행하면 에러)
        // 테이블이 있는지 확인하고 들어가야한다.
        // SELECT 획득 칼럼 명 FROM 테이블 명 WHERE 조건
        // SQL 에 ? 는 데이터가 들어갈 자리이다. 이 sql을 실행하려면 ?에 해당되는 데이터를 지정한 후 실행해야 한다.
        val query = "SELECT name FROM sqlite_master WHERE type = 'table' AND name = ?"

        // 위 sql 실행..
        // test_tb 라는 데이터가 ?에 들어가서 실행된다. 결국 test_tb라는 테이블이 이미 만들어 진 것인지 확인한다.
        val cursor = db.rawQuery(query, arrayOf("test_tb"))
        val exists = cursor.count > 0
        cursor.close()

        if (!exists) {
            // 테이블이 없다면, 새로 만든다
            // test_tb 라는 이름의 테이블을 만든다.
            // _id, title, contents 컬럼을 둔다.
            // _id 가 식별자(PRIMARY KEY)칼럼이고 값이 자동 증가(AUTOINCREMENT)된다.
            db.execSQL("CREATE TABLE test_tb(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "content INTEGER)")
            // 테스트를 위한 데이터를 저장한다.
            for (i in 1..10) {
                db.execSQL("INSERT INTO test_tb(title, content) VALUES(?, ?)", arrayOf("title $i", "content $i"))
            }
        }

        // test_tb 테이블에서 모든 데이터를 획득하라
        // 컬럼 명이 들어갈 위치에  * : 모든 컬럼
        // WHERE 로 어느 조건의 데이터를 명시할 수 있다.
        val cursor1 = db.rawQuery("SELECT * FROM test_tb", null)
        // Cursor : 로 지칭.. cursor을 움직여서 row 선택한다.

        var result = ""
        while (cursor1.moveToNext()) {
            // 선택된 row의 column data를 획득한다. column의 index로
            result += cursor1.getString(1)
            result += " : "
            result += cursor1.getString(2)
            result += "\n"
        }
        Log.d("jay", result)
        cursor1.close()
    }
}