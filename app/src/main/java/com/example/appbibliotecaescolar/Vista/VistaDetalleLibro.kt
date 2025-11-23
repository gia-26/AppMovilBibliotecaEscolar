package com.example.appbibliotecaescolar.Vista

import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.R

class VistaDetalleLibro : AppCompatActivity() {
    private lateinit var tvTituloLibroVD : TextView
    private lateinit var tvAutorVD : TextView
    private lateinit var tvisbnVD : TextView
    private lateinit var tvGeneroVD : TextView
    private lateinit var tvEditorialVD : TextView
    private lateinit var tvAnioEdiVD : TextView
    private lateinit var tvNoEstanteVD : TextView
    private lateinit var tvEdicionVD : TextView
    private lateinit var tvSinopsis : TextView
    private lateinit var tvEstadoVD : TextView
    private lateinit var imgLibroVD : ImageView
    private lateinit var btnCerrar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista_detalle_libro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTituloLibroVD = findViewById(R.id.tvTituloLibroVD)
        tvAutorVD = findViewById(R.id.tvAutorVD)
        tvisbnVD = findViewById(R.id.tvisbnVD)
        tvGeneroVD = findViewById(R.id.tvGeneroVD)
        tvEditorialVD = findViewById(R.id.tvEditorialVD)
        tvAnioEdiVD = findViewById(R.id.tvAnioEdiVD)
        tvNoEstanteVD = findViewById(R.id.tvNoEstanteVD)
        tvEdicionVD = findViewById(R.id.tvEdicionVD)
        tvSinopsis = findViewById(R.id.tvSinopsis)
        tvEstadoVD = findViewById(R.id.tvEstadoVD)
        imgLibroVD = findViewById(R.id.imgLibroVD)
        btnCerrar = findViewById(R.id.btnCerrar)

        //Obtener los datos de las peliculas del intent
        val libTitulo = intent.getStringExtra("lib_titulo")
        val libAutor = intent.getStringExtra("lib_autor")
        val libISBN = intent.getStringExtra("lib_isbn")
        val libGenero = intent.getStringExtra("lib_genero")
        val libEditorial = intent.getStringExtra("lib_editorial")
        val libAnioEdicion = intent.getStringExtra("lib_anioEdicion")
        val libNoEstante = intent.getStringExtra("lib_noEstante")
        val libEdicion = intent.getStringExtra("lib_edicion")
        val libSinopsis = intent.getStringExtra("lib_sinopsis")
        val libEstado = intent.getStringExtra("lib_estado")
        val libImg = intent.getStringExtra("lib_img")

        //Cargar la imagen de la película usando Glide
        Glide.with(this)
            .load(Constants.URL_IMAGEN + libImg)
            .into(imgLibroVD)

        //Asignar los datos a las vistas
        tvTituloLibroVD.text = libTitulo
        tvAutorVD.text = Html.fromHtml("<b>Autor:</b> $libAutor", Html.FROM_HTML_MODE_LEGACY)
        tvisbnVD.text = Html.fromHtml("<b>ISBN:</b> $libISBN", Html.FROM_HTML_MODE_LEGACY)
        tvGeneroVD.text = Html.fromHtml("<b>Género:</b> $libGenero", Html.FROM_HTML_MODE_LEGACY)
        tvEditorialVD.text = Html.fromHtml("<b>Editorial:</b> $libEditorial", Html.FROM_HTML_MODE_LEGACY)
        tvAnioEdiVD.text = Html.fromHtml("<b>Año de edición:</b> $libAnioEdicion", Html.FROM_HTML_MODE_LEGACY)
        tvNoEstanteVD.text = Html.fromHtml("<b>No. de estante:</b> $libNoEstante", Html.FROM_HTML_MODE_LEGACY)
        tvEdicionVD.text = Html.fromHtml("<b>Edición:</b> $libEdicion", Html.FROM_HTML_MODE_LEGACY)
        tvSinopsis.text = Html.fromHtml("<b>Sinopsis:</b><br>$libSinopsis", Html.FROM_HTML_MODE_LEGACY)
        tvEstadoVD.text = libEstado

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}