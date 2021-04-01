package com.example.storeinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.storeinfirebase.notification.MyApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var names:EditText
    lateinit var numbers:EditText
    lateinit var address: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        db = Firebase.firestore
        names=findViewById(R.id.name)
        numbers=findViewById(R.id.number)
        address=findViewById(R.id.addres)
        findViewById<Button>(R.id.btn_save).setOnClickListener {
            if (names.text.isNotEmpty()&&numbers.text.isNotEmpty()&&address.text.isNotEmpty()){
                addUserToDB(names.text.toString(),numbers.text.toString(),address.text.toString())
            }
        }
        addUserToDB("Tasneem","5","Gaza")
        addUserToDB("Tasneem","5","Gaza")
        addUserToDB("Tasneem","5","Gaza")
        addUserToDB("Tasneem","5","Gaza")

    }
    private fun addUserToDB( name: String, number: String, addres: String) {

        val user = hashMapOf( "name" to name, "number" to number, "addres" to addres)

        db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Sign up Successfully", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)


                    val manager = MyApp(this)
                    manager.showSmallNotification(
                            1,
                            getString(R.string.app_name),
                            "You are successfully Rigister ${name}",
                            intent, R.drawable.tt


                    )
                    startActivity(intent)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Sign Up Failed" + exception.message, Toast.LENGTH_SHORT).show()
                }
    }
}