package com.example.appbibliotecaescolar.Vista.Contracts

import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRegistro

interface RegistroContract {
    fun mostrarMensaje(mensaje : String)
    fun volverLogin()
    fun mostrarDatosUsuario(usuario : List<ClsDatosRegistro>)
}