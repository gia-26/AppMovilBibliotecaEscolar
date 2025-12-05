package com.example.appbibliotecaescolar

import com.example.appbibliotecaescolar.Modelo.ifaceApiService
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsInfoBiblioteca
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRespuesta
import retrofit2.Call
import retrofit2.Callback
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log

object TestApi {

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()

    private val ApiService = retrofit.create(ifaceApiService::class.java)

    fun probarObtenerInfoBibliotecaSimple() {
        ApiService.obtenerInfoBiblioteca().enqueue(object : Callback<List<ClsInfoBiblioteca>> {
            override fun onResponse(
                call: Call<List<ClsInfoBiblioteca>>,
                response: Response<List<ClsInfoBiblioteca>>
            ) {
                Log.d("PRUEBA_RETROFIT", "INFO_BIBLIOTECA => ${response.body()}")
            }

            override fun onFailure(call: Call<List<ClsInfoBiblioteca>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR => ${t.message}")
            }
        })
    }

    fun probarIniciarSesionSimple(email: String, password: String) {
        ApiService.iniciarSesion("login", email, password).enqueue(object : Callback<List<ClsDatosRespuesta>> {
            override fun onResponse(
                call: Call<List<ClsDatosRespuesta>>,
                response: Response<List<ClsDatosRespuesta>>
            ) {
                Log.d("PRUEBA_LOGIN", "RESPUESTA_LOGIN => ${response.body()}")
            }

            override fun onFailure(call: Call<List<ClsDatosRespuesta>>, t: Throwable) {
                Log.e("PRUEBA_LOGIN", "ERROR => ${t.message}")
            }
        })
    }
}