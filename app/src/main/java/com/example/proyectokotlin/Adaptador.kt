package com.example.proyectokotlin

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adaptador(var listaLibros:MutableList<Libro>, val actividad: Activity):RecyclerView.Adapter<Adaptador.ViewHolder>() {
    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var tvId:TextView = v.findViewById(R.id.item_tvId)
        var tvTitulo:TextView = v.findViewById(R.id.item_tvTitulo)
        var tvAutor:TextView = v.findViewById(R.id.item_tvAutor)
        var tvGenero:TextView = v.findViewById(R.id.item_tvGenero)
        var tvFecha:TextView = v.findViewById(R.id.item_tvFecha)
        var urlImagen:ImageView = v.findViewById(R.id.item_imagen)
        var tvLeido:TextView = v.findViewById(R.id.item_tvLeido)
        var posicion:Int = 0
        init {
            v.setOnClickListener{
                val bundle = bundleOf("id" to this.posicion)
                (actividad as MainActivity).findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_SecondFragment_to_ThirdFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvId.text = listaLibros[position].id.toString()
        holder.tvTitulo.text = listaLibros[position].titulo
        holder.tvAutor.text = listaLibros[position].autor
        holder.tvGenero.text = listaLibros[position].genero
        holder.tvFecha.text = listaLibros[position].fecha
        Glide.with(actividad).load(listaLibros[position].imagen).into(holder.urlImagen)
        holder.tvLeido.text = listaLibros[position].leido
        holder.posicion = listaLibros[position].id

    }

    override fun getItemCount(): Int {
        return listaLibros.count()
    }
}