package com.srikanthpuram.samples.recyclerviewkotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.srikanthpuram.samples.recyclerviewkotlin.db.EmployeeDbTable
import kotlinx.android.synthetic.main.activity_add_employee.*

class AddEmployeeActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    private val CHOOSE_IMAGE_REQUEST = 1

    private var imageUri: Uri = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
        Log.d(TAG,"onCreate() ")
    }

    fun saveEmployee(v: View) {
        if (add_employee_name_edit.isBlank() || add_employee_designation_edit.isBlank()) {
            Log.d(TAG, "Profile not saved: name or designation is missing.")
            displayErrorMessage("Employee Name and Designation are required")
            return
        } else if (imageUri == Uri.EMPTY) {
            Log.d(TAG, "Profile not Saved: image missing.")
            displayErrorMessage("Add a profile picture to your profile.")
            return
        }

        //Log.d("STRING RESOURCE TEST", getString(R.string.drink_water_desc))

        val employeeName = add_employee_name_edit.text.toString()
        val employeeDesignation = add_employee_designation_edit.text.toString()
        val employeeData = EmployeeData(employeeName, employeeDesignation, imageUri)

        val id = EmployeeDbTable(this).store(employeeData)

        if (id == -1L) {
            displayErrorMessage("Profile could not be saved...try again later.")
        } else {
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            finish()
        }
    }

    fun chooseImage(v: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Choose image for habit")
        startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)

        Log.d(TAG, "Intent to choose image sent...")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.data != null) {
            Log.d(TAG, "An image was chosen by the user.")

            val bitmapUri = data.data

            bitmapUri?.let {
                this.imageUri = bitmapUri
                profile_picture_iv.setImageURI(bitmapUri)
                Log.d(TAG, "Read image bitmap and updated image view.")
            }
        }
    }

    private fun displayErrorMessage(message: String) {
        error_tv.text = message
        error_tv.visibility = View.VISIBLE
    }
}

private fun EditText.isBlank() = this.text.toString().isBlank()
