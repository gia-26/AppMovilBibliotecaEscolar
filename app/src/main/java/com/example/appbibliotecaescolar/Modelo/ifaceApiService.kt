package com.example.appbibliotecaescolar.Modelo

import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRegistro
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRespuesta
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsInfoBiblioteca
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsLibros
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsPrestamos
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ifaceApiService {
    @GET("apiInfoBiblioteca.php")
    fun obtenerInfoBiblioteca() : Call<List<ClsInfoBiblioteca>>
    @GET("apiPrestamos.php")
    fun obtenerPrestamos() : Call<List<ClsPrestamos>>

    @GET("apiLibros.php")
    fun obtenerLibros(): Call<List<ClsLibros>>

    //Api de inicio de sesion
    @FormUrlEncoded
    @POST("apiLogin.php")
    fun iniciarSesion(
        @Field("action") action: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<List<ClsDatosRespuesta>>

    @FormUrlEncoded
    @POST("apiBuscarUsuario.php")
    fun buscarUsuario(
        @Field("idUsuario") idUsuario: String
    ): Call<List<ClsDatosRegistro>>

    @FormUrlEncoded
    @POST("apiAcceso.php")
    fun registrarUsuario(
        @Field("action") action: String,
        @Field("idUsuario") idUsuario: String,
        @Field("password") password: String
    ): Call<List<ClsDatosRespuesta>>
}