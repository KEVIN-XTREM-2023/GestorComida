package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class opcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        regresar()
        inicio()
        menu()
        reservas()
        clientes()
        val datosUsuario=intent.extras
        val nombreUsu = datosUsuario?.getString("nombre")
        println("global1----> "+nombreUsu)
    }

    fun regresar(){
        var boton : Button = findViewById(R.id.btnRegresar)
        boton.setOnClickListener {
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
    }
    fun inicio(){
        var boton : ImageButton = findViewById(R.id.imgbtnInicio)
        boton.setOnClickListener {
            val intent = Intent(this,inicioActivity::class.java)
            startActivity(intent)
        }
    }
    fun menu(){
        var boton : ImageButton = findViewById(R.id.imgbtnMenu)
        boton.setOnClickListener {
            val intent = Intent(this,menuActivity::class.java)
            startActivity(intent)
        }
    }

    fun reservas(){
        var boton : ImageButton = findViewById(R.id.imgbtnReservas)
        boton.setOnClickListener {
            val intent = Intent(this,ReservasActivity::class.java)
            startActivity(intent)
        }
    }
    fun clientes(){
        var boton : ImageButton = findViewById(R.id.imgbtnClientes)
        boton.setOnClickListener {
            val intent = Intent(this,ClientesActivity::class.java)
            startActivity(intent)
        }
    }
}