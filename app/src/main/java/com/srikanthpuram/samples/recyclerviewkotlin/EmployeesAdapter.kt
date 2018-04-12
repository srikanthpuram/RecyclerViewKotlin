package com.srikanthpuram.samples.recyclerviewkotlin

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by spuram on 4/9/2018.
 */
class EmployeesAdapter(val employeesList: List<EmployeeData>, val context: Context) :RecyclerView.Adapter<EmployeesAdapter.EmployeeDataViewHolder>() {

    private val TAG = javaClass.simpleName

    class EmployeeDataViewHolder(val employeeItemView: View) : RecyclerView.ViewHolder(employeeItemView)

    override fun onBindViewHolder(holder: EmployeeDataViewHolder?, position: Int) {
        if(holder != null) {
            val employeeData = employeesList[position]
            holder.employeeItemView.user_name.text = employeeData.name
            holder.employeeItemView.designation.text = employeeData.designation
            holder.employeeItemView.profile_image.setImageDrawable(null)
            //holder.employeeItemView.profile_image.setImageURI(employeeData.imageUri)

            val width : Int = holder.employeeItemView.profile_image.width
            Picasso.with(context).load(employeeData.imageUri).into(holder.employeeItemView.profile_image)


            Log.d(TAG,"Position: "+position+"  employeeData.imageUri"+employeeData.imageUri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return EmployeeDataViewHolder(view)
    }

    override fun getItemCount() = employeesList.size
}