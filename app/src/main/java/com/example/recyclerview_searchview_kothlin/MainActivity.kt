package com.example.recyclerview_searchview_kothlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var list = ArrayList<ExampeItem>()
    lateinit var adapter : ExampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add(ExampeItem(R.drawable.ic_android,"one","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"two","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"three","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"four","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"five","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"six","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"seven","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"eight","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"nine","ONE"))
        list.add(ExampeItem(R.drawable.ic_android,"ten","ONE"))


        adapter= ExampleAdapter(list);
        val recyclerView : RecyclerView = recycler_view;
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.example_menu, menu)


        val searchItem = menu.findItem(R.id.action_search)
       val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search View Hint")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)

                return false


            }

        })


        return true
    }
}
