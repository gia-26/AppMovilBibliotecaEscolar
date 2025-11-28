package com.example.appbibliotecaescolar.Presentador

import com.example.appbibliotecaescolar.Modelo.InicioModel
import com.example.appbibliotecaescolar.Vista.Contracts.InicioContract

class InicioPresenter (val vista : InicioContract) {
    val model = InicioModel()

    fun recuperarInfo()
    {
        model.inicializarApiService()
        model.obtenerInfo { exito, mensaje, info ->
            if (exito){
                vista.recuperarInfo(info)
                vista.mostrarMensaje(mensaje)
            }
            else
                vista.mostrarMensaje(mensaje)
        }
    }
}