package com.example.element.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.element.Element
import com.example.element.R
import com.example.element.adapters.Adapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ObjectOutputStream

class MainActivity : AppCompatActivity() {
    private var elements: ArrayList<Element> = arrayListOf(
        Element(
            "Ваша карточка",
            0
        )
    )
    private var adapter = Adapter(elements)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (savedInstanceState != null) {
            elements = savedInstanceState.getParcelableArrayList<Element>("ELEMENTS")
            adapter = Adapter(elements)
        }

        recyclerView.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("ELEMENTS", elements)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                val element = Element(" ", 0)
                elements.add(element)
                adapter.update()
                true
            }
            R.id.action_save -> {
                val objectOutputStream = ObjectOutputStream(openFileOutput("FILENAME.txt", 0))
                objectOutputStream.use {
                    for (element in elements)
                        objectOutputStream.writeObject(element)
                }
                Toast.makeText(this, "Объекты загружены в файл!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
