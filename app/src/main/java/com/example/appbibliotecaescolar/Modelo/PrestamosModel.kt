package com.example.appbibliotecaescolar.Modelo

import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsPrestamos
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrestamosModel {
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

    fun obtenerPrestamos(idUsuario: String, callback: (Boolean, String, List<ClsPrestamos>) -> Unit)
    {
        apiService.obtenerPrestamos(idUsuario).enqueue(object: Callback<List<ClsPrestamos>>
        {
            override fun onResponse(call: Call<List<ClsPrestamos>>, response: Response<List<ClsPrestamos>>)
            {
                if (response.isSuccessful)
                {
                    response.body()?.let { prestamos ->
                        if (prestamos.isEmpty())
                            callback(true, "El usuario aún no cuenta con ningún préstamo", prestamos)
                        else
                            callback(true, "Prestamos recuperados correctamente", prestamos)
                    }
                }
                else
                {
                    callback(true, "Error: ${response.message()}", emptyList())
                }
            }

            override fun onFailure(call: Call<List<ClsPrestamos>?>, t: Throwable) {
                callback(true, "Error: ${t.message}", emptyList())
            }
        })
    }
}