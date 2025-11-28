package com.example.appbibliotecaescolar.Vista.Contracts

import com.example.appbibliotecaescolar.Modelo.DataClass.ClsLibros

interface LibrosContract {
    fun mostrarLibros(libros : List<ClsLibros>)
    fun mostrarMensaje(mensaje : String)
}