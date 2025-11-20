package com.example.appbibliotecaescolar.Vista

import com.example.appbibliotecaescolar.Modelo.ClsLibros

interface LibrosContract {
    fun mostrarLibros(libros : List<ClsLibros>)
    fun mostrarMensaje(mensaje : String)
}