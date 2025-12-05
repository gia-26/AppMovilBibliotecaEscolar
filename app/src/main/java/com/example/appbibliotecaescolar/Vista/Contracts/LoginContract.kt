package com.example.appbibliotecaescolar.Vista.Contracts

interface LoginContract {
    fun redirigirInicio(idUsuario : String)
    fun mostrarMensaje(mensaje : String)
}