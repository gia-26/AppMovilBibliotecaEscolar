package com.example.appbibliotecaescolar.Modelo

import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsInfoBiblioteca
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsLibros
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InicioModel {
    private lateinit var apiService: ifaceApiService

    fun inicializarApiService() {
        //Configuracion de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }

    fun obtenerInfo(callback: (Boolean, String, List<ClsInfoBiblioteca>) -> Unit)
    {
        apiService.obtenerInfoBiblioteca().enqueue(object: Callback<List<ClsInfoBiblioteca>>
        {
            override fun onResponse(call: Call<List<ClsInfoBiblioteca>>, response: Response<List<ClsInfoBiblioteca>>)
            {
                if (response.isSuccessful)
                {
                    response.body()?.let { info ->
                        callback(true, "Información recuperada correctamente", info)
                    }
                }
                else
                {
                    callback(true, "Error: ${response.message()}", emptyList())
                }
            }

            override fun onFailure(call: Call<List<ClsInfoBiblioteca>?>, t: Throwable) {
                callback(true, "Error: ${t.message}", emptyList())
            }
        })
    }
}