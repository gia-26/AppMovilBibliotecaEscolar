package com.example.appbibliotecaescolar.Modelo.Adapters

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsLibros
import com.example.appbibliotecaescolar.R
import com.example.appbibliotecaescolar.Vista.VistaDetalleLibro

class LibrosAdaptador (val contexto : Context, val listaLibros : List<ClsLibros>) : RecyclerView.Adapter<LibrosAdaptador.LibroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        //Se hará el vinculo con el layout libro_layout.xml
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.libro_layout,
            parent,
            false
        )

        return LibroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        //Se van a extraer los datos de las listas y los colocamos en cada control de layout
        val libro = listaLibros[position]
        holder.tvTituloLibro.text = libro.titulo
        holder.tvAutor.text = "Autor: " + libro.autor
        holder.tvSinopsis.text = Html.fromHtml("Sinopsis: <br>${libro.sinopsis}", Html.FROM_HTML_MODE_LEGACY)

        //Cargar la imagen usando Glide
        Glide.with(contexto)
            .load(Constants.URL_IMAGEN + libro.imagen) //Usar la URL de la imagen holaaaa
            .into(holder.imgLibro)
        holder.imgLibro.setOnClickListener {
            verDetalle(libro)
        }
    }

    fun verDetalle(libro : ClsLibros)
    {
        //Se llama al activity detalle
        val intent = Intent(contexto, VistaDetalleLibro::class.java).apply {
            putExtra("lib_id", libro.idLibro)
            putExtra("lib_titulo", libro.titulo)
            putExtra("lib_autor", libro.autor)
            putExtra("lib_isbn", libro.isbn)
            putExtra("lib_genero", libro.genero)
            putExtra("lib_editorial", libro.editorial)
            putExtra("lib_anioEdicion", libro.anioEdicion)
            putExtra("lib_noEstante", libro.noEstante)
            putExtra("lib_edicion", libro.edicion)
            putExtra("lib_sinopsis", libro.sinopsis)
            putExtra("lib_estado", libro.estado)
            putExtra("lib_img", libro.imagen)
        }
        contexto.startActivity(intent)
    }

    override fun getItemCount(): Int {
        //Se retorna el tamaño de la lista
        return listaLibros.size
    }

    class LibroViewHolder(control: View) : RecyclerView.ViewHolder(control)
    {
        //Controles del layout de cada Libro
        val tvTituloLibro : TextView = itemView.findViewById(R.id.tvTituloLibro)
        val tvAutor : TextView = itemView.findViewById(R.id.tvAutor)
        val tvSinopsis : TextView = itemView.findViewById(R.id.tvSinopsis)
        val imgLibro : ImageView = itemView.findViewById(R.id.imgLibro)
    }
}