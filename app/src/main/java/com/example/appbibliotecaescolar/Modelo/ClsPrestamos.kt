package com.example.appbibliotecaescolar.Modelo

data class ClsPrestamos(
    val tituloLibro: String,
    val autor: String,
    val fechaPrestamo: String,
    val fechaDevolucion: String,
    val estado : String,
    val imagen : String
)
