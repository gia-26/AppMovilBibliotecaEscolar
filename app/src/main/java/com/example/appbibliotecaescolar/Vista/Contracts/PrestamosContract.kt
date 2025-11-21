package com.example.appbibliotecaescolar.Vista.Contracts

import com.example.appbibliotecaescolar.Modelo.ClsPrestamos

interface PrestamosContract {
    fun mostrarPrestamos(prestamos : List<ClsPrestamos>)
    fun mostrarMensaje(mensaje : String)
}