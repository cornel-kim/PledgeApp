package com.modcom.pledgeapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)//app initialization
        val rootref:DatabaseReference = FirebaseDatabase.getInstance().reference//reference to the db
        val demoref:DatabaseReference = rootref.child("members")//database table name members

        btnSUBMIT.setOnClickListener {
            //set up firebase
            //put the edittexts to hashmap
            val userData: MutableMap<String, String> = HashMap();
            userData["ID"] = txtID.text.toString()
            userData["Name"] = txtName.text.toString()
            userData["Amount"] = txtAmount.text.toString()


            //save them to demoref
            demoref.child(txtID.text.toString()).setValue(userData)//Done
            Toast.makeText(this, "Saved to the database", Toast.LENGTH_SHORT).show()

//            demoref.setValue("Our first class 2020")

        }
    }
}