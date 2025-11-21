package com.example.appbibliotecaescolar.Presentador

import com.example.appbibliotecaescolar.Modelo.PrestamosModel
import com.example.appbibliotecaescolar.Vista.Contracts.PrestamosContract

class PrestamosPresenter (val vista : PrestamosContract) {
    val modelo = PrestamosModel()

    fun recuperarPrestamos()
    {
        modelo.inicializarApiService()
        modelo.obtenerPrestamos { exito, mensaje, prestamos ->
            if (exito){
                vista.mostrarMensaje(mensaje)
                vista.mostrarPrestamos(prestamos)
            }
            else
                vista.mostrarMensaje(mensaje)
        }
    }
}