package com.example.appbibliotecaescolar.Vista

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRegistro
import com.example.appbibliotecaescolar.Presentador.RegistroPresenter
import com.example.appbibliotecaescolar.R
import com.example.appbibliotecaescolar.Vista.Contracts.RegistroContract
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class Registro : AppCompatActivity(), RegistroContract {
    private lateinit var btnQR : Button
    private lateinit var btnRegistrar : Button
    private lateinit var tvNombre : TextView
    private lateinit var tvApellidoP : TextView
    private lateinit var tvApellidoM : TextView
    private lateinit var edtPass : EditText
    private lateinit var edtPassConfirm : EditText

    private var datosUsuario : ClsDatosRegistro? = null
    private var registerPresent = RegistroPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnQR = findViewById(R.id.btnQR)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        tvNombre = findViewById(R.id.tvNombre)
        tvApellidoP = findViewById(R.id.tvApellidoP)
        tvApellidoM = findViewById(R.id.tvApellidoM)
        edtPass = findViewById(R.id.edtUsuario2)
        edtPassConfirm = findViewById(R.id.edtPassConfirm)

        btnQR.setOnClickListener(this::solicitarPermisoCamara)
        btnRegistrar.setOnClickListener(this::registrar)
    }
    private fun registrar(v : View)
    {
        val pass = edtPass.text.toString()
        val passConfir = edtPassConfirm.text.toString()

        if (!pass.isEmpty() && !passConfir.isEmpty()){
            if (pass == passConfir) {
                val user = datosUsuario
                if (user != null)
                    registerPresent.registrar(user.idUsuario, pass, user.idTipoUsuario)
                else
                    mostrarMensaje("Escanea un código QR antes del registro")
            }
            else
                mostrarMensaje("Las contraseñas son diferentes")
        }
        else
            mostrarMensaje("Escanea un código QR antes del registro")
    }
    private fun solicitarPermisoCamara(v : View) {
        when {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                iniciarEscaneoQR()
            }
            else -> {
                permisoCamaraLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    private val permisoCamaraLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            iniciarEscaneoQR()
        } else {
            mostrarMensaje("Se necesita permiso de cámara para escanear QR")
        }
    }

    private fun iniciarEscaneoQR() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Escanea el código QR")
            setCameraId(0)
            setBeepEnabled(false)
            setBarcodeImageEnabled(true)
            setOrientationLocked(false)
        }
        escanearQR.launch(options)
    }

    private val escanearQR = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            registerPresent.procesarResultadoQR(result.contents)
        } else {
            mostrarMensaje("Escaneo cancelado")
        }
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    override fun volverLogin() {
        finish()
    }

    override fun mostrarDatosUsuario(usuario: List<ClsDatosRegistro>) {
        // Como solo hay un resultado, accedemos directamente al primer elemento [0]
        datosUsuario = usuario[0]
        val user = usuario[0]
        tvNombre.text = Html.fromHtml("<b>Nombre:</b> ${user.nombre}", Html.FROM_HTML_MODE_LEGACY)
        tvApellidoP.text = Html.fromHtml("<b>Apellido Paterno:</b> ${user.apellidoP}", Html.FROM_HTML_MODE_LEGACY)
        tvApellidoM.text = Html.fromHtml("<b>Apellido Materno:</b> ${user.apellidoM}", Html.FROM_HTML_MODE_LEGACY)
    }
}