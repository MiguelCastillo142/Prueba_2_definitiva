package com.example.prueba_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prueba_2.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var signInAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener{
            signInAttempts++

            if (signInAttempts > 1) {
                Toast.makeText(this, "Ya se ha intentado iniciar sesión. Por favor, espera.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = binding.etusuario.text.toString()
            val password = binding.etpassword.text.toString()

            if(email.isEmpty()) {
                binding.etusuario.error = "Por favor ingrese un usuario"
                signInAttempts = 0
                return@setOnClickListener
            }

            if(password.isEmpty()) {
                binding.etpassword.error = "Por favor ingrese una contraseña"
                signInAttempts = 0
                return@setOnClickListener
            }

            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    signInAttempts = 0
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Inicio correcto", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, PostLogin::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            signInAttempts = 0 // Reiniciar el contador de intentos si hay una excepción
            Toast.makeText(this, "Error al intentar iniciar sesión", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}


