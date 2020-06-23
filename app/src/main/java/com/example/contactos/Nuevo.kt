package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class Nuevo : AppCompatActivity() {

    var fotoIndex:Int = 0
    val fotos = arrayOf(R.drawable.foto_01, R.drawable.foto_02, R.drawable.foto_03, R.drawable.foto_04, R.drawable.foto_05, R.drawable.foto_06)
    var foto:ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

         foto = findViewById<ImageView>(R.id.ivFoto)

        foto?.setOnClickListener {
            seleccionarFoto()
        }
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
                        MainActivity.agregarContacto(Contacto(campos.get(0), campos.get(1), campos.get(2),campos.get(3).toInt(),campos.get(4).toFloat(),campos.get(5), campos.get(6),campos.get(7),obtenerFoto(fotoIndex)))
                        finish()
                        Log.d("No ELEMENTOS", MainActivity.contactos?.count().toString())
                    }

                    return true
                }
                else ->{  return super.onOptionsItemSelected(item)}
            }

        }
        fun seleccionarFoto(){
            val builder = AlertDialog.Builder( this)
            builder.setTitle("Selecciona imagen de perfil")

            val adaptadorDialogo = ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item)
            adaptadorDialogo.add("Foto 01")
            adaptadorDialogo.add("Foto 02")
            adaptadorDialogo.add("Foto 03")
            adaptadorDialogo.add("Foto 04")
            adaptadorDialogo.add("Foto 05")
            adaptadorDialogo.add("Foto 06")
            builder.setAdapter(adaptadorDialogo){
                dialog, which->
                fotoIndex = which
                foto?.setImageResource(obtenerFoto(fotoIndex))

            }
            builder.setNegativeButton("Cancelar"){
                dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }

        fun obtenerFoto(index: Int ): Int{
           return fotos.get(index)
        }
    }
