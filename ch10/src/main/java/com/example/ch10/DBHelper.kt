package com.example.ch10

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 앱을 위한 DBMS 의 관라적인 코드 (table, create, alter, drop) 추상화
// testdb - db file
// 1 - 상위 생성자에 db version 정보 지정한다.
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    // 앱 인스톨 후 최초 한번, 주로 table create
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE tb_user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name, " +
                "address," +
                "age INTEGER)")
    }

    // 상위 생성자에 전달되 db version이 변경 될때마다 호출됨
    // db 구조 변경을 위해서..
    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE tb_user")
        onCreate(db)
    }


    // 만약 잘못만들었다면 상단 1의 매개변수를 2로 수정한다.
    // onUpgrade의 drop을 삭제하고 onCreate하거나
    // drop이 아닌 alter문을 onUpgrade에서 작성하면 된다.
}