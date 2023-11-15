package com.example.prueba_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.prueba_2.databinding.ActivityPostLoginBinding


class PostLogin : AppCompatActivity() {

    // Agregar viewbinding
    private lateinit var binding: ActivityPostLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar bottomNav
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    // Muestra el fragment de home
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, HomeFragment()).commit()
                    true
                }
                R.id.nav_settings -> {
                    // Muestra el fragment de settings
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SettingsFragment()).commit()
                    true
                }
                else -> false
            }
        }

        binding.bottomNav.setOnItemReselectedListener {
            when (it.itemId){
                R.id.nav_home -> Toast.makeText(this,"Ya estás en la vista home", Toast.LENGTH_LONG).show()
                R.id.nav_settings -> Toast.makeText(this,"Ya estás en la vista datos", Toast.LENGTH_LONG).show()
            }
        }

    }
}