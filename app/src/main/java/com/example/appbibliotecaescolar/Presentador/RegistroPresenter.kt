package com.example.appbibliotecaescolar.Presentador

import com.example.appbibliotecaescolar.Modelo.RegistroModel
import com.example.appbibliotecaescolar.Vista.Contracts.RegistroContract

class RegistroPresenter (val vista : RegistroContract) {
    val modelo = RegistroModel()

    fun procesarResultadoQR(contenidoQR: String) {
        modelo.inicializarApiService()
        try {
            if (!contenidoQR.isEmpty()) {
                modelo.recuperarDatosUsuario(contenidoQR) { exito, mensaje, usuario ->
                    if (exito && usuario.isNotEmpty()) {
                        vista.mostrarDatosUsuario(usuario)
                        vista.mostrarMensaje(mensaje)
                    }
                    else
                        vista.mostrarMensaje(mensaje)
                }
            }
            else
                vista.mostrarMensaje("Error el nombre está vacío")
        } catch (e: Exception) {
            vista.mostrarMensaje("Error al procesar QR: ${e.message}")
        }
    }

    fun registrar(idUsuario : String, password : String, idTipoUsuario : String)
    {
        modelo.inicializarApiService()
        modelo.registrarUsuario(idUsuario, password, idTipoUsuario) { exito, mensaje ->
            if (exito){
                vista.mostrarMensaje(mensaje)
                vista.volverLogin()
            }
            else
                vista.mostrarMensaje(mensaje)
        }
    }
}