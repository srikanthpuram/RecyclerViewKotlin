package com.srikanthpuram.samples.recyclerviewkotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by spuram on 4/11/2018.
 */
class EmployeeDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES = "CREATE TABLE ${EmployeeEntry.TABLE_NAME} (" +
            "${EmployeeEntry._ID} INTEGER PRIMARY KEY," +
            "${EmployeeEntry.EMPL_NAME_COL} TEXT," +
            "${EmployeeEntry.EMPL_DESIGNATION_COL} TEXT," +
            "${EmployeeEntry.EMPL_PROFILE_IMAGE_COL} TEXT" +
            ")"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${EmployeeEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}