package com.example.ch8

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8.databinding.ActivityTest2Binding

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // actionbar를 toolbar로 대체
        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // 메뉴를 구성하기 위해서 자동 호출하게된다. (내가 호출하지 않아도..)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 1. 코드에서 메뉴를 추가하는 방법
//        menu?.add(0, 0, 0, "저장")
//        menu?.add(0, 1, 0, "삭제")

        // menu xml 이용하는 방법
        // 아래의 코드만으로 화면에 메뉴 출력한다.
        menuInflater.inflate(R.menu.menu_test2, menu)

        // 아래의 코드는 SearchView 때문에 사용하는 방식
        // SearchView가 포함된 MenuItem을 얻고, 그 MenuItem에 추가된 SearchView를 획득한다.
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "hint"
        searchView.setOnQueryTextListener(queryTextListener)

        return super.onCreateOptionsMenu(menu)
    }

    val queryTextListener = object : SearchView.OnQueryTextListener {
        // 검색어 입력 순간마다.. 매개변수가 현재 입력된 검색어다.
        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

        // 검색을 위해서 키보드의 검색 버튼을 누른 순간
        override fun onQueryTextSubmit(query: String?): Boolean {
            Toast.makeText(this@Test2Activity, query, Toast.LENGTH_SHORT).show()
            return false
        }
    }

    // 메뉴 이벤트 함수
    // 현재 선택한 메뉴
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_setting -> Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show()
            R.id.menu_save -> Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}