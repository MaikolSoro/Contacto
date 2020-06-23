package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_nuevo.*

class Detalle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // btn para regresar
        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val index = intent.getStringExtra("ID")!!.toInt()
        //Log.d("INDEX", index.toString())

        val contacto = MainActivity.obtenerContacto(index)

        var tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvEmpresa = findViewById<TextView>(R.id.tvEmpresa)
        val tvEdad  = findViewById<TextView>(R.id.tvEdad)
        val tvPeso = findViewById<TextView>(R.id.tvPeso)
        val tvDireccion = findViewById<TextView>(R.id.tvDireccion)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefono)
        val tvEmail  = findViewById<TextView>(R.id.tvEmail)

        tvNombre.text = contacto.nombre + " " + contacto.apellidos
        tvEmpresa.text = contacto.empresa
        tvEdad.text = contacto.edad.toString() + " años "
        tvPeso.text = contacto.peso.toString() + " kg "
        tvDireccion.text = contacto.direccion
        tvTelefono.text = contacto.telefono
        tvEmail.text = contacto.email


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {

            android.R.id.home-> {
                finish()
                return true
            }
            R.id.iEditar -> {
                return true
            }

            R.id.iEliminar ->{
                return true
            }
            else ->{  return super.onOptionsItemSelected(item)}
        }

    }
}