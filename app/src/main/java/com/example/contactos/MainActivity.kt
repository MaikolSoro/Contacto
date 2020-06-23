package com.example.contactos

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    var lista:ListView? = null

    companion object {

        var contactos: ArrayList<Contacto>? = null
        var adaptador: AdaptadorCustom? = null
        fun agregarContacto(contacto:Contacto) {
           adaptador?.addItem(contacto)
        }

        fun obtenerContacto(index:Int): Contacto{
            return adaptador?.getItem(index) as Contacto
        }

        fun eliminarContacto(index: Int) {
            adaptador?.removeItem(index)
        }

        fun actualizarContacto(index: Int, nuevoContacto:Contacto) {
            adaptador?.updateItemn(index, nuevoContacto)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        contactos = ArrayList()
        contactos?.add(Contacto("Maikol", "Soro", "Avantica", 21, 50.0F, "Santa Rita", "86223186", "maikolsoro.z1998@gmail.com", R.drawable.foto_01))

         lista = findViewById<ListView>(R.id.lista)
         adaptador = AdaptadorCustom( this,contactos!!)

        lista?.adapter = adaptador

        lista?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent( this, Detalle::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda = menu?.findItem(R.id.searchView)
        val searchView = itemBusqueda?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "BuscarContacto..."

        searchView.setOnQueryTextFocusChangeListener {v, hasFocus ->
            // preparar los datos
        }
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                // filtrar
                adaptador?.filtrar(p0!!)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {

                // filtrar
                return  true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.iNuevo ->{
                val intent = Intent(this, Nuevo::class.java)
                startActivity(intent)
                return true
            }
            else ->{  return super.onOptionsItemSelected(item)}
        }

    }

    override fun onResume() {
        super.onResume()
        adaptador?.notifyDataSetChanged()
    }

}