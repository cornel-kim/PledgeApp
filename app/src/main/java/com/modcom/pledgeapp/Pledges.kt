package com.modcom.pledgeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pledges.*

class Pledges : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pledges)
        //get firebase references
        FirebaseApp.initializeApp(this)//app initialization
        val rootref: DatabaseReference = FirebaseDatabase.getInstance().reference//reference to the db
        val demoref: DatabaseReference = rootref.child("members")//database table name members

        //listener to fetch items from the db
        val listener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                tvpledges.setText("")
                if (snapshot.exists()){
                    //loop through the databse using for loop. Pull two columns for name and pledge
                    for (singleSnapshot in snapshot.children){
                        tvpledges.append("Confirmed: ${singleSnapshot.child("Name").getValue(String::class.java)} has pledged")
                        tvpledges.append("KSH: ${singleSnapshot.child("Amount").getValue(String::class.java)}")
                        tvpledges.append("\n\n")
                    }

                }
                else{
                    tvpledges.setText("Error, No data found")
                }
            }//end of snapshot

            override fun onCancelled(error: DatabaseError) {
              tvpledges.setText("No database connection")
            }

        }//end of listener
        demoref.addValueEventListener(listener)
    }
}