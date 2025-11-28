package com.example.appbibliotecaescolar.Modelo

import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsLibros
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LibrosModel {
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

    fun obtenerLibros(callback: (Boolean, String, List<ClsLibros>) -> Unit)
    {
        apiService.obtenerLibros().enqueue(object: Callback<List<ClsLibros>>
        {
            override fun onResponse(call: Call<List<ClsLibros>>, response: Response<List<ClsLibros>>)
            {
                if (response.isSuccessful)
                {
                    response.body()?.let { libros ->
                        callback(true, "Libros recuperados correctamente", libros)
                    }
                }
                else
                {
                    callback(true, "Error: ${response.message()}", emptyList())
                }
            }

            override fun onFailure(call: Call<List<ClsLibros>?>, t: Throwable) {
                callback(true, "Error: ${t.message}", emptyList())
            }
        })
    }
}