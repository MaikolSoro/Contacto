package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
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

                    var campos = ArrayList<String>()
                    campos.add(nombre.text.toString())
                    campos.add(apellidos.text.toString())
                    campos.add(empresa.text.toString())
                    campos.add(edad.text.toString())
                    campos.add(peso.text.toString())
                    campos.add(direccion.text.toString())
                    campos.add(telefono.text.toString())
                    campos.add(email.text.toString())

                    var flag = 0
                    for (campo in campos) {
                        if(campo.isNullOrEmpty())
                            flag++
                    }

                    if(flag > 0){
                        Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                    } else {
                        MainActivity.agregarContacto(Contacto(campos.get(0), campos.get(1), campos.get(2),campos.get(3).toInt(),campos.get(4).toFloat(),campos.get(5), campos.get(6),campos.get(7   ),R.drawable.foto_03))
                        finish()
                        Log.d("No ELEMENTOS", MainActivity.contactos?.count().toString())
                    }

                    return true
                }
                else ->{  return super.onOptionsItemSelected(item)}
            }

        }
    }
