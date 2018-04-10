package com.srikanthpuram.samples.recyclerviewkotlin

/**
 * Created by spuram on 4/9/2018.
 */
data class EmployeeData (val name: String, val designation: String, val image: Int)

    fun getEmployeesData(): List<EmployeeData> {
        return listOf(
                EmployeeData("Srikanth Puram","Mobile Developer",R.drawable.perm_group_personal_info),
                EmployeeData("Satish Avunoori","Mobile Developer",R.drawable.walk),
                EmployeeData("Sheila Nava","Scrum Master",R.drawable.water),
                EmployeeData("Terry Day","Project Manager",R.drawable.perm_group_personal_info),
                EmployeeData("Daniel Hoffman","Business Analyst",R.drawable.walk),
                EmployeeData("Romal Shah","Team Lead",R.drawable.water),
                EmployeeData("Chritina Hewigg","Sales",R.drawable.perm_group_personal_info),
                EmployeeData("Praveen Nulu","QA Analyst",R.drawable.walk),
                EmployeeData("Chelsea Cerel","Product Owner",R.drawable.water)

        )
    }

