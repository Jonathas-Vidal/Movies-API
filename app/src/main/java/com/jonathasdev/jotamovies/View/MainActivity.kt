package com.jonathasdev.jotamovies.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jonathasdev.jotamovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val authenticator = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.txtCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email != "" && senha != "") {
                authenticator.signInWithEmailAndPassword(
                    email, senha
                ).addOnSuccessListener {

                    val intent = Intent(this, FilmsActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        this,
                        "Logado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Verifique todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}