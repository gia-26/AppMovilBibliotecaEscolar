package com.example.appbibliotecaescolar.Vista

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsLibros
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsPrestamos
import com.example.appbibliotecaescolar.Modelo.Adapters.LibrosAdaptador
import com.example.appbibliotecaescolar.Modelo.Adapters.PrestamosAdaptador
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsInfoBiblioteca
import com.example.appbibliotecaescolar.Presentador.InicioPresenter
import com.example.appbibliotecaescolar.Presentador.LibrosPresenter
import com.example.appbibliotecaescolar.Presentador.PrestamosPresenter
import com.example.appbibliotecaescolar.R
import com.example.appbibliotecaescolar.Vista.Contracts.InicioContract
import com.example.appbibliotecaescolar.Vista.Contracts.LibrosContract
import com.example.appbibliotecaescolar.Vista.Contracts.PrestamosContract
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class Main : AppCompatActivity() {
    private lateinit var contenedor : FrameLayout
    private lateinit var btnInicio : Button
    private lateinit var btnCatalogo : Button
    private lateinit var btnPrestamos : Button
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var playerView: StyledPlayerView
    private lateinit var tvQuienesSomos : TextView
    private lateinit var tvMision : TextView
    private lateinit var tvVision : TextView
    private lateinit var tvObjetivo : TextView

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

        val inicioView = layoutInflater.inflate(R.layout.activity_inicio, contenedor, false)

        tvQuienesSomos = inicioView.findViewById(R.id.tvQuienesSomos)
        tvMision = inicioView.findViewById(R.id.tvMision)
        tvVision = inicioView.findViewById(R.id.tvVision)
        tvObjetivo = inicioView.findViewById(R.id.tvObjetivo)
        playerView = inicioView.findViewById(R.id.playerView)

        // Cargar datos
        val inicioPresenter = InicioPresenter(object : InicioContract {
            override fun recuperarInfo(info: List<ClsInfoBiblioteca>) {
                val infoBiblioteca = info[0]
                tvQuienesSomos.text = infoBiblioteca.quienesSomos
                tvMision.text = infoBiblioteca.mision
                tvVision.text = infoBiblioteca.vision
                tvObjetivo.text = infoBiblioteca.objetivo
            }

            override fun mostrarMensaje(mensaje: String) {
                Toast.makeText(this@Main, mensaje, Toast.LENGTH_LONG).show()
            }
        })

        inicioPresenter.recuperarInfo()

        reproducir(playerView)

        contenedor.addView(inicioView)
    }

    fun reproducir(v : View)
    {
        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer
        playerView.useController = true

        //Construye la URI del video
        val videoUri = ("android.resource://" + packageName + "/" + R.raw.video_bioetica).toUri()
        val mediaItem = MediaItem.fromUri(videoUri)

        //Configura el video en el reproductor
        exoPlayer.setMediaItem(mediaItem)

        //Prepara y reproduce el video
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    override fun onPause()
    {
        super.onPause()
        exoPlayer.pause()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.release()
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        exoPlayer.release()
    }
    fun mostrarCatalogo(v : View)
    {
        contenedor.removeAllViews()

        val catalogoView = layoutInflater.inflate(R.layout.activity_ver_catalogo, contenedor, false)

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
        //val prestamosView = layoutInflater.inflate(R.layout.activity_ver_prestamos, contenedor, false)

        val catalogoView = layoutInflater.inflate(R.layout.activity_ver_catalogo, contenedor, false)

        val rcvLista = catalogoView.findViewById<RecyclerView>(R.id.rcvLista)
        rcvLista.layoutManager = LinearLayoutManager(this)

        val adaptador = PrestamosAdaptador(this, mutableListOf())
        rcvLista.adapter = adaptador

        // Cargar datos
        val prestamosPresenter = PrestamosPresenter(object : PrestamosContract {
            override fun mostrarPrestamos(prestamos: List<ClsPrestamos>) {
                runOnUiThread {
                    val adaptador = PrestamosAdaptador(this@Main, prestamos)
                    rcvLista.adapter = adaptador
                }
            }

            override fun mostrarMensaje(mensaje: String) {
                runOnUiThread {
                    Toast.makeText(this@Main, mensaje, Toast.LENGTH_LONG).show()
                }
            }
        })
        prestamosPresenter.recuperarPrestamos()

        contenedor.addView(catalogoView)
    }
}