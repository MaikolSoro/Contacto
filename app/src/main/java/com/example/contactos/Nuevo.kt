package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.widget.Toolbar

class Nuevo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_nuevo, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item?.itemId) {
                R.id.iCrearNuevo ->{
                   // Crear un nuevo elemenento de tipo Contacto
                    val nombre = findViewById<EditText>(R.id.tvNombre)
                    val apellidos = findViewById<EditText>(R.id.tvApellidos)
                    val empresa = findViewById<EditText>(R.id.tvEmpresa)
                    val edad = findViewById<EditText>(R.id.tvEdad)
                    val peso = findViewById<EditText>(R.id.tvPeso)
                    val telefono = findViewById<EditText>(R.id.tvTelefono)
                    val email = findViewById<EditText>(R.id.tvEmail)
                    val direccion = findViewById<EditText>(R.id.tvDireccion)

                    // VALIDAR LOS CAMPOS
                    MainActivity.agregarContacto(Contacto(nombre.text.toString(), apellidos.text.toString(), empresa.text.toString(), edad.text.toString().toInt(), peso.text.toString().toFloat(), direccion.text.toString(), telefono.text.toString(), email.text.toString(),R.drawable.foto_03))
                    finish()
                    Log.d("No ELEMENTOS", MainActivity.contactos?.count().toString())
                    return true
                }
                else ->{  return super.onOptionsItemSelected(item)}
            }

        }
    }
