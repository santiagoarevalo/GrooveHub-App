package com.example.groovehub_app

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.text.InputType
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.groovehub_app.databinding.ActivityLoginBinding
import com.example.groovehub_app.databinding.ActivityRegisterBinding
import com.example.groovehub_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }


        binding.buttonLogIn.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPassword.text.toString()

            if(!email.isEmpty() && !pass.isEmpty()) {


                Firebase.auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {

                    val fbUser = Firebase.auth.currentUser
                    if (fbUser!!.isEmailVerified) {

                        //1. Get user in firebase
                        Firebase.firestore.collection("users").document(fbUser.uid).get()
                            .addOnSuccessListener {
                                val user = it.toObject(User::class.java)
                                //2.Save the user
                                savaUser(user!!)
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }

                    } else {
                        Toast.makeText(this, "Su Email no esta verificado", Toast.LENGTH_SHORT)
                            .show()
                    }


                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Ingrese los datos", Toast.LENGTH_SHORT).show()
            }
        }


        binding.forgotPassLogin.setOnClickListener {
            if(!binding.inputEmail.text.toString().isEmpty()) {
                Firebase.auth.sendPasswordResetEmail(binding.inputEmail.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(
                            this,
                            "Revise su correo " + binding.inputEmail.text.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
            }else{
                Toast.makeText(this, "Ingrese el Email", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageVisorLogin.setOnClickListener {
            if(binding.inputPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                binding.inputPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }else{
                binding.inputPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            }
        }

    }

    override fun onBackPressed() {
        if(isTaskRoot){
            finish()
        }
    }

    fun savaUser(user: User){
        val sp = getSharedPreferences("appmoviles", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("user",json).apply()
    }


}