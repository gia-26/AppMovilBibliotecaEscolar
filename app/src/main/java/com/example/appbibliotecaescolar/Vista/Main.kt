package com.example.appbibliotecaescolar.Vista

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
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

class Main : AppCompatActivity() {
    private lateinit var contenedor : FrameLayout
    private lateinit var btnInicio : Button
    private lateinit var btnCatalogo : Button
    private lateinit var btnPrestamos : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contenedor)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        contenedor = findViewById(R.id.contenedor)
        btnInicio = findViewById(R.id.btnInicio)
        btnCatalogo = findViewById(R.id.btnCatalogo)
        btnPrestamos = findViewById(R.id.btnPrestamos)

        btnInicio.setOnClickListener(this::mostrarInicio)
        btnCatalogo.setOnClickListener(this::mostrarCatalogo)
        btnPrestamos.setOnClickListener(this::mostrarPrestamos)
        mostrarInicio(btnInicio)
    }

    fun mostrarInicio(v : View)
    {
        contenedor.removeAllViews()
        val pantalla = layoutInflater.inflate(R.layout.activity_inicio, contenedor, false)
        contenedor.addView(pantalla)
    }
    fun mostrarCatalogo(v : View)
    {
        contenedor.removeAllViews()

        // Inflar solo el contenido del RecyclerView
        val catalogoView = layoutInflater.inflate(R.layout.activity_ver_catalogo, contenedor, false)

        // Configurar RecyclerView manualmente
        val rcvLista = catalogoView.findViewById<RecyclerView>(R.id.rcvLista)
        rcvLista.layoutManager = LinearLayoutManager(this)

        val adaptador = LibrosAdaptador(this, mutableListOf())
        rcvLista.adapter = adaptador

        // Cargar datos
        val librosPresenter = LibrosPresenter(object : LibrosContract {
            override fun mostrarLibros(libros: List<ClsLibros>) {
                runOnUiThread {
                    val adaptador = LibrosAdaptador(this@Main, libros)
                    rcvLista.adapter = adaptador
                }
            }

            override fun mostrarMensaje(mensaje: String) {
                runOnUiThread {
                    Toast.makeText(this@Main, mensaje, Toast.LENGTH_LONG).show()
                }
            }
        })
        librosPresenter.recuperarLibros()

        contenedor.addView(catalogoView)
    }
    fun mostrarPrestamos(v : View)
    {
        contenedor.removeAllViews()
        val pantalla = layoutInflater.inflate(R.layout.activity_ver_prestamos, contenedor, false)
        contenedor.addView(pantalla)
    }
}