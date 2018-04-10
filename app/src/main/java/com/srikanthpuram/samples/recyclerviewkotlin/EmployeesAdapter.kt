package com.srikanthpuram.samples.recyclerviewkotlin

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by spuram on 4/9/2018.
 */
class EmployeesAdapter(val employeesList: List<EmployeeData>) :RecyclerView.Adapter<EmployeesAdapter.EmployeeDataViewHolder>() {

    class EmployeeDataViewHolder(val employeeItemView: View) : RecyclerView.ViewHolder(employeeItemView)

    override fun onBindViewHolder(holder: EmployeeDataViewHolder?, position: Int) {
        if(holder != null) {
            holder.employeeItemView.user_name.text = employeesList[position].name
            holder.employeeItemView.designation.text = employeesList[position].designation
            holder.employeeItemView.profile_image.setImageResource(employeesList[position].image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return EmployeeDataViewHolder(view)
    }

    override fun getItemCount() = employeesList.size
}