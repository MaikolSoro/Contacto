package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_nuevo.*

class Detalle : AppCompatActivity() {

    var index: Int = 0;
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

        mapearDatos()

    }

    fun mapearDatos() {

        val contacto = MainActivity.obtenerContacto(index)

        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvEmpresa = findViewById<TextView>(R.id.tvEmpresa)
        val tvEdad  = findViewById<TextView>(R.id.tvEdad)
        val tvPeso = findViewById<TextView>(R.id.tvPeso)
        val tvDireccion = findViewById<TextView>(R.id.tvDireccion)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefono)
        val tvEmail  = findViewById<TextView>(R.id.tvEmail)
        val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        tvNombre.text = contacto.nombre + " " + contacto.apellidos
        tvEmpresa.text = contacto.empresa
        tvEdad.text = contacto.edad.toString() + " años "
        tvPeso.text = contacto.peso.toString() + " kg "
        tvDireccion.text = contacto.direccion
        tvTelefono.text = contacto.telefono
        tvEmail.text = contacto.email
        ivFoto.setImageResource(contacto.foto)
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
                val intent = Intent( this, Nuevo::class.java)
                intent.putExtra("ID", index.toString())
                startActivity(intent)
                return true
            }

            R.id.iEliminar ->{
                MainActivity.eliminarContacto(index)
                finish()
                return true
            }
            else ->{  return super.onOptionsItemSelected(item)}
        }

    }

    override fun onResume() {
        super.onResume()
        mapearDatos()
    }
}