package com.example.appbibliotecaescolar.Modelo.DataClass

data class ClsLibros(
    val idLibro: String,
    val titulo: String,
    val autor: String,
    val isbn: String,
    val genero: String,
    val editorial: String,
    val anioEdicion: String,
    val noEstante: String,
    val edicion: String,
    val sinopsis: String,
    val sinopsisCompleta: String,
    val imagen: String,
    val estado : String
)
