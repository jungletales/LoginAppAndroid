package com.nupura.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var register = findViewById<TextView>(R.id.register)
        var logIn = findViewById<Button>(R.id.signIn)
        var logEmail = findViewById<EditText>(R.id.loginEmail)
        var logPassword = findViewById<EditText>(R.id.loginPassword)
        logIn.setOnClickListener { it
            when {
                TextUtils.isEmpty(logEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(logPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "please enter the password.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else -> {
                    val email: String = logEmail.toString().trim{ it <= ' '}
                    val password: String = logPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "You are logged in successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val  intent =
                                    Intent(this, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra(
                                    "user_id",
                                    FirebaseAuth.getInstance().currentUser!!.uid
                                )
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }

        register.setOnClickListener { it
            startActivity(Intent(this, RegisterUser::class.java))
        }
    }
}