package com.srikanthpuram.samples.recyclerviewkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ScrollView
import com.srikanthpuram.samples.recyclerviewkotlin.db.EmployeeDbTable
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    val EMPLOYEE_ADD_REQUEST_CODE = "AddEmployee"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate()")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.adapter = EmployeesAdapter(getEmployeesData())
        recycler_view.adapter = EmployeesAdapter(EmployeeDbTable(this).readAllEmployeesData(),this)
        val dividerItemDecoration = DividerItemDecoration(recycler_view.getContext(),DividerItemDecoration.VERTICAL)
        recycler_view.addItemDecoration(dividerItemDecoration)

        val scrollView = ScrollView(this)
        scrollView.setOnDragListener { view, event ->
            Log.d(TAG, "Dragging...")
            true
        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_employee){
            //start AddEmployee Activity
            switchTo(AddEmployeeActivity::class.java)
        }
        return true
    }

    private fun switchTo(c: Class<*>) {
        val intent = Intent(this, c)
        startActivity(intent)
    }
}
