package com.example.contactos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdaptadorCustom( var contexto: Context, items:ArrayList<Contacto>):BaseAdapter() {

    // Almacenar los elementos que se van en el ListView

    var items: ArrayList<Contacto>? = null
    var copiaItems: ArrayList<Contacto>?= null

    init {
        this.items = ArrayList(items)
        this.copiaItems = items;
    }
    //Asociar el rendirisado de los elementos
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var viewHolder : ViewHolder? = null
        var vista: View? = p1

        if(vista == null) {
            vista = LayoutInflater.from(contexto).inflate(R.layout.template_contacto, null)
            viewHolder = ViewHolder(vista)
            vista.tag = viewHolder
        } else {
            viewHolder = vista.tag as? ViewHolder
        }

        val item = getItem(p0) as Contacto

        // Asiganción de valores a elementos gráficos
        viewHolder?.nombre?.text = item.nombre + " " + item.apellidos
        viewHolder?.empresa?.text = item.empresa
        viewHolder?.foto?.setImageResource(item.foto)

        return vista!!
    }

    override fun getItem(p0: Int): Any {

        return this.items?.get(p0)!!
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getCount(): Int {
        //regresar el número de mi lista
        return this.items?.count()!!
    }

    // agregar los items de nuevo contacto
    fun addItem(item:Contacto) {
        copiaItems?.add(item)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    // delete the item
    fun removeItem(index: Int) {
        copiaItems?.removeAt(index)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    //update the item

    fun updateItemn(index: Int, newItem:Contacto) {
        copiaItems?.set(index, newItem)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }


    // EL filtro para buscar
    fun filtrar(str:String) {
        items?.clear()

        if(str.isEmpty()){
            items = ArrayList(copiaItems)
            notifyDataSetChanged()
            return
        }

        var busqueda = str
        busqueda = busqueda.toLowerCase()

        for( item in copiaItems!!){

            val nombre = item.nombre.toLowerCase()

            if(nombre.contains(busqueda)){
                items?.add(item)

            }
        }

        notifyDataSetChanged()
    }


    private  class  ViewHolder(vista:View) {
        var nombre:TextView? = null
        var foto: ImageView? = null
        var empresa:TextView? = null

        init{
            nombre = vista.findViewById(R.id.tvNombre)
            empresa = vista.findViewById(R.id.tvEmpresa)
            foto = vista.findViewById(R.id.ivFoto)
        }
    }




}