package com.example.appbibliotecaescolar.Vista.Contracts

import com.example.appbibliotecaescolar.Modelo.DataClass.ClsInfoBiblioteca

interface InicioContract {
    fun recuperarInfo(info : List<ClsInfoBiblioteca>)
    fun mostrarMensaje(mensaje : String)
}