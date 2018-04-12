package com.srikanthpuram.samples.recyclerviewkotlin.db

import android.provider.BaseColumns

/**
 * Created by spuram on 4/11/2018.
 */

val DATABASE_NAME = "employee.db"
val DATABASE_VERSION = 10

object EmployeeEntry : BaseColumns {
    val TABLE_NAME = "employee_table"
    val _ID = "id"
    val EMPL_NAME_COL = "empl_name"
    val EMPL_DESIGNATION_COL = "empl_designation"
    val EMPL_PROFILE_IMAGE_COL = "empl_profile_image"
}