package com.example.appbibliotecaescolar.Modelo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.R

class PrestamosAdaptador (val contexto : Context, val listaPrestamos : List<ClsPrestamos>) : RecyclerView.Adapter<PrestamosAdaptador.PrestamoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrestamoViewHolder {
        //Se hará el vinculo con el layout prestamo_layout.xml
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.prestamo_layout,
            parent,
            false
        )
        return PrestamoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrestamoViewHolder, position: Int) {
        //Se van a extraer los datos de las listas y los colocamos en cada control de layout
        val prestamo = listaPrestamos[position]
        holder.tvTitLibP.text = prestamo.tituloLibro
        holder.tvAutorP.text = prestamo.autor
        holder.tvFechaPres.text = prestamo.fechaPrestamo
        holder.tvFechaDev.text = prestamo.fechaDevolucion
        holder.tvEstadoP.text = prestamo.estado


        //Cargar la imagen usando Glide
        Glide.with(contexto)
            .load(Constants.URL_IMAGEN + prestamo.imagen) //Usar la URL de la imagen
            .into(holder.imgLibP)
    }

    override fun getItemCount(): Int {
        return listaPrestamos.size
    }

    class PrestamoViewHolder(control: View) : RecyclerView.ViewHolder(control)
    {
        //Controles del layout de cada préstamo
        val tvTitLibP : TextView = itemView.findViewById(R.id.tvTitLibP)
        val tvAutorP : TextView = itemView.findViewById(R.id.tvAutor)
        val tvFechaPres : TextView = itemView.findViewById(R.id.tvFechaPres)
        val tvFechaDev : TextView = itemView.findViewById(R.id.tvFechaDev)
        val tvEstadoP : TextView = itemView.findViewById(R.id.tvEstadoP)
        val imgLibP : ImageView = itemView.findViewById(R.id.imgLibP)
    }
}