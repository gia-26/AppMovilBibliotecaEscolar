package com.example.appbibliotecaescolar.Vista

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbibliotecaescolar.Modelo.ClsLibros
import com.example.appbibliotecaescolar.Modelo.LibrosAdaptador
import com.example.appbibliotecaescolar.Presentador.LibrosPresenter
import com.example.appbibliotecaescolar.R
import com.example.appbibliotecaescolar.Vista.Contracts.LibrosContract

class VerCatalogo : AppCompatActivity(), LibrosContract {
    /*private lateinit var rcvLista : RecyclerView
    private var librosPresenter = LibrosPresenter(this)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_ver_catalogo)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rcvLista = findViewById(R.id.rcvLista)
        rcvLista.layoutManager = LinearLayoutManager(this)

        librosPresenter.recuperarLibros()*/
    }

    override fun mostrarLibros(libros: List<ClsLibros>) {
        /*val adaptador = LibrosAdaptador(this@VerCatalogo, libros)
        rcvLista.adapter = adaptador*/
    }

    override fun mostrarMensaje(mensaje: String) {
        //Toast.makeText(baseContext, mensaje, Toast.LENGTH_SHORT).show()
    }
}