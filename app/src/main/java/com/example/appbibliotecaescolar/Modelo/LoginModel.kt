package com.example.appbibliotecaescolar.Modelo

import android.util.Log
import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRespuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginModel {
    private lateinit var apiService: ifaceApiService

    fun inicializarApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ifaceApiService::class.java)
    }
    fun acceder(email: String, password: String, callback: (Boolean, String, String) -> Unit) {
        apiService.iniciarSesion(action = "login", email = email, password = password)
            .enqueue(object : Callback<List<ClsDatosRespuesta>> {
                override fun onResponse(
                    call: Call<List<ClsDatosRespuesta>>,
                    response: Response<List<ClsDatosRespuesta>>
                ) {
                    if (response.isSuccessful) {
                        val datos = response.body()
                        if (!datos.isNullOrEmpty() && datos[0].Estado == "Correcto") {
                            callback(true, "Inicio de sesión correcto", datos[0].user_id)
                        } else {
                            callback(false, "No se encontraron datos del usuario.", "")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.d("API_RESULT", "Respuesta: ${response.body()}")
                        callback(false, "Error en la respuesta del servidor: $errorBody", "")
                    }
                }

                override fun onFailure(call: Call<List<ClsDatosRespuesta>>, t: Throwable) {
                    Log.e("API_FAIL", "Error: ${t.message}")
                    callback(false, "Error en la conexión: ${t.message}", "")
                }
            })
    }
}