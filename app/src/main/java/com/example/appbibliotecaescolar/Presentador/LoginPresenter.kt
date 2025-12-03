package com.example.appbibliotecaescolar.Presentador

import com.example.appbibliotecaescolar.Modelo.LoginModel
import com.example.appbibliotecaescolar.Vista.Contracts.LoginContract

class LoginPresenter (val vista : LoginContract) {
    var modelo = LoginModel()

    fun acceder(email : String, password : String)
    {
        modelo.inicializarApiService()
        modelo.acceder(email, password){ exito, mensaje, idUsuario ->
            if (exito)
            {
                vista.mostrarMensaje(mensaje)
                vista.redirigirInicio(idUsuario)
            }
            else
            {
                vista.mostrarMensaje(mensaje)
            }
        }
    }
}