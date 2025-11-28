package com.example.appbibliotecaescolar.Vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appbibliotecaescolar.Presentador.LoginPresenter
import com.example.appbibliotecaescolar.R
import com.example.appbibliotecaescolar.Test
import com.example.appbibliotecaescolar.Vista.Contracts.LoginContract

class Login : AppCompatActivity(), LoginContract {
    lateinit var edtEmail : EditText
    lateinit var edtPass : EditText
    lateinit var btnAcceder : Button
    lateinit var tvRegistrar : TextView
    var loginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtEmail = findViewById(R.id.edtUsuario)
        edtPass = findViewById(R.id.edtPassword)
        btnAcceder = findViewById(R.id.btnIngresar)
        tvRegistrar = findViewById(R.id.tvRegistrar)

        tvRegistrar.setOnClickListener(this::redirigirRegistro)

        //Listener para el botón de Login
        btnAcceder.setOnClickListener(this::iniciarSesion)

        val test = Test
        test.obtenerPrestamos()
        test.obtenerLibros()
        test.buscarUsuario("ALUM001")
    }

    fun iniciarSesion(v : View)
    {
        val email = edtEmail.text.toString()
        val password = edtPass.text.toString()
        loginPresenter.acceder(email, password)
    }

    fun redirigirRegistro(v : View)
    {
        val intent = Intent(this, Registro::class.java)
        startActivity(intent)
    }

    override fun redirigirInicio() {
        val intent = Intent(this@Login, Main::class.java)
        startActivity(intent)
        finish()
    }

    override fun mostrarMensaje(mensaje : String) {
        Toast.makeText(this@Login, mensaje, Toast.LENGTH_SHORT).show()
    }
}