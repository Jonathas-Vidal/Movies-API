package com.jonathasdev.jotamovies.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jonathasdev.jotamovies.databinding.ActivityCadstroBinding

class CadastroActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCadstroBinding.inflate(layoutInflater)
    }
    private val authenticator = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnCadastro.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email != "" && senha != "") {
                authenticator.createUserWithEmailAndPassword(
                    email, senha
                ).addOnSuccessListener {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        this,
                        "Cadastrado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this,
                    "Verifique todos os campos",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}