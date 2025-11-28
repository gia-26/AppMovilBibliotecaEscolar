package com.example.appbibliotecaescolar.Presentador

import com.example.appbibliotecaescolar.Modelo.LibrosModel
import com.example.appbibliotecaescolar.Vista.Contracts.LibrosContract

class LibrosPresenter (val vista : LibrosContract) {
    val modelo = LibrosModel()

    fun recuperarLibros()
    {
        modelo.inicializarApiService()
        modelo.obtenerLibros { exito, mensaje, libros ->
            if (exito) {
                vista.mostrarMensaje(mensaje)
                vista.mostrarLibros(libros)
            } else {
                vista.mostrarMensaje(mensaje)
            }
        }
    }
}