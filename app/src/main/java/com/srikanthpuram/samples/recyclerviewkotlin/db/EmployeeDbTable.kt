package com.srikanthpuram.samples.recyclerviewkotlin.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.srikanthpuram.samples.recyclerviewkotlin.EmployeeData
import com.srikanthpuram.samples.recyclerviewkotlin.db.EmployeeEntry.TABLE_NAME
import com.srikanthpuram.samples.recyclerviewkotlin.db.EmployeeEntry.EMPL_DESIGNATION_COL
import com.srikanthpuram.samples.recyclerviewkotlin.db.EmployeeEntry.EMPL_PROFILE_IMAGE_COL
import com.srikanthpuram.samples.recyclerviewkotlin.db.EmployeeEntry.EMPL_NAME_COL
import com.srikanthpuram.samples.recyclerviewkotlin.db.EmployeeEntry._ID
import java.io.ByteArrayOutputStream

/**
 * Created by spuram on 4/11/2018.
 */
class EmployeeDbTable(context: Context) {

    private val TAG = EmployeeDbTable::class.java.simpleName

    private val dbHelper = EmployeeDb(context)

    fun store(employee: EmployeeData): Long {
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        with(values) {
            put(EMPL_NAME_COL, employee.name)
            put(EMPL_DESIGNATION_COL, employee.designation)
            put(EMPL_PROFILE_IMAGE_COL, employee.imageUri.toString())
        }

        val id = db.transaction {
            insert(TABLE_NAME, null, values)
        }

        Log.d(TAG, "Stored new employee to the DB $employee")

        return id
    }

    fun readAllEmployeesData(): List<EmployeeData> {

        val columns = arrayOf(_ID, EMPL_NAME_COL, EMPL_DESIGNATION_COL, EMPL_PROFILE_IMAGE_COL)

        val order = "$_ID ASC"

        val db = dbHelper.readableDatabase

        val cursor = db.doQuery(TABLE_NAME, columns, orderBy = order)

        return parseEmployeessFrom(cursor)
    }

    private fun parseEmployeessFrom(cursor: Cursor): MutableList<EmployeeData> {
        val employees = mutableListOf<EmployeeData>()
        while (cursor.moveToNext()) {
            val title = cursor.getString(EMPL_NAME_COL)
            val desc = cursor.getString(EMPL_DESIGNATION_COL)
            val bitmap = cursor.getUri(EMPL_PROFILE_IMAGE_COL)
            employees.add(EmployeeData(title, desc, bitmap))
        }
        cursor.close()

        return employees
    }

    private fun toByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

}

private fun SQLiteDatabase.doQuery(table: String, columns: Array<String>, selection: String? = null,
                                   selectionArgs: Array<String>? = null, groupBy: String? = null,
                                   having: String? = null, orderBy: String? = null): Cursor {
    return query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
}

private fun Cursor.getString(columnName: String) = getString(getColumnIndex(columnName))

private fun Cursor.getBitmap(columnName: String): Bitmap {
    val bytes = getBlob(getColumnIndex(columnName))
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

private fun Cursor.getUri(columnName: String): Uri = Uri.parse(getString(getColumnIndex(columnName)))

private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T {
    beginTransaction()
    val result = try {
        val returnValue = function()
        setTransactionSuccessful()

        returnValue
    } finally {
        endTransaction()
    }
    close()

    return result
}











