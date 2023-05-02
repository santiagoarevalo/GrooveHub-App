package com.example.groovehub_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.groovehub_app.databinding.ActivityRegisterBinding
import com.example.groovehub_app.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener(::register)

        val intent = Intent(this,LoginActivity::class.java)



        val callback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(intent)

            }
        }

        onBackPressedDispatcher.addCallback(this,callback)


        binding.imageVisorRegister.setOnClickListener {
            if(binding.editTextPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                binding.editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }else{
                binding.editTextPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            }
        }



    }

    fun register(view: View){
        //Register user in the db of firebase
        if(!binding.editTextEmail.text.toString().isEmpty() && !binding.editTextPassword.text.toString().isEmpty() &&
            !binding.editTextName.text.toString().isEmpty() && !binding.editTextLastName.text.toString().isEmpty() &&
            !binding.editTextUserName.text.toString().isEmpty() && !binding.editTextEmail.text.toString().isEmpty()) {

            Firebase.auth.createUserWithEmailAndPassword(
                binding.editTextEmail?.text.toString(),
                binding.editTextPassword?.text.toString()
            ).addOnSuccessListener {
                //Register all data or other data of user
                var id = Firebase.auth.currentUser?.uid

                val user = User(
                    id!!,
                    binding.editTextName.text.toString(),
                    binding.editTextLastName.text.toString(),
                    binding.editTextUserName.text.toString(),
                    binding.editTextEmail.text.toString()
                )

                Firebase.firestore.collection("users").document(id).set(user).addOnSuccessListener {
                    sendVerificationEmail()
                    finish()
                }
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "Ingrese los datos", Toast.LENGTH_SHORT).show()
        }

    }

    fun sendVerificationEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            Toast.makeText(this,"Verifique su Email antes de entrar",Toast.LENGTH_LONG).show()

        }?.addOnFailureListener {
            Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
        }
    }


}