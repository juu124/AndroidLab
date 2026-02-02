package com.example.ch6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch6.databinding.ActivityTest1Binding
import com.example.ch6.databinding.ItemRecyclerBinding

class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val list = mutableListOf<String>()
        for (i in 1..20) {
            list.add("Item $i")
        }
        binding.main.layoutManager = LinearLayoutManager(this)
        binding.main.adapter = MyAdapter(list)
        binding.main.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}

// 항목을 구성하기 위한 뷰를 선언해서 가지는 역할자.
// adapter에서 활용한다.
class MyViewHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

// 항목을 구성(데이터 출력, 이벤트 등록등)
// 매개변수 : 가지고 있는 데이터 전달 받는다
// 제네릭으로 사용하는 뷰 홀더 타입을 지정한다.
// 항목을 구성하는
class MyAdapter(val datas: MutableList<String>) : RecyclerView.Adapter<MyViewHolder>() {
    // 항목 구성을 하기 위한 뷰홀더를 준비하기 위해서 자동호출된다.
    // 준비된 뷰홀더 객체를 생성하고 리턴한다.
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    // 각 항목을 구성하기 위해서 자동 호출된다.
    // 데이터를 찍을 때 알고리즘이 필요하거나 등등 여러 작업이 필요하다.
    // 항목 갯수 만큼 반복 호출된다.
    // position - 항목 index
    // holder - onCreateViewHolder() 에서 리턴시킨 객체.. 항목을 구성하기 위한 뷰를 가지는 객체
    override fun onBindViewHolder(
        holder: MyViewHolder, position: Int
    ) {
        holder.binding.itemData.text = datas[position]
    }

    // 함목 갯수를 판단하기 위해서 자동 호출
    // 해당 리턴 값이 0이면 아무것도 안 나올 뿐
    override fun getItemCount(): Int {
        return datas.size
    }

}