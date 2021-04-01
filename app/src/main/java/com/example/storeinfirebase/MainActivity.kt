package com.example.storeinfirebase


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeinfirebase.model.Users
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore
    lateinit var Frinds: FirestoreRecyclerAdapter<Users, UserViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        db = Firebase.firestore
        getAllUser()

    }

    private fun getAllUser() {
        val query = db.collection("users").orderBy("name")
        val options =
                FirestoreRecyclerOptions.Builder<Users>().setQuery(query, Users::class.java).build()

        Frinds = object : FirestoreRecyclerAdapter<Users, UserViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                val view = LayoutInflater.from(this@MainActivity)
                        .inflate(R.layout.item_user, parent, false)
                return UserViewHolder(view)
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: Users) {
                holder.name.text = model.name
                holder.number.text = model.numbers
                holder.address.text = model.addres



            }


        }

        findViewById<RecyclerView>(R.id.rc_user).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rc_user).adapter = Frinds

    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name = view.findViewById<TextView>(R.id.name_item)
        var number = view.findViewById<TextView>(R.id.number_item)
        var address = view.findViewById<TextView>(R.id.addres_item)

    }

    override fun onStart() {
        super.onStart()
        Frinds.startListening()
    }

    override fun onStop() {
        super.onStop()
        Frinds.stopListening()
    }

}

