package com.example.appbibliotecaescolar.Modelo

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ifaceApiService {
    //Api de inicio de sesion
    @FormUrlEncoded
    @POST("apiLogin.php")
    fun iniciarSesion(
        @Field("action") action: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<List<ClsDatosRespuesta>>
}