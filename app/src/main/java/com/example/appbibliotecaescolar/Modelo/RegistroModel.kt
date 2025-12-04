package com.example.appbibliotecaescolar.Modelo

import android.util.Log
import com.example.appbibliotecaescolar.Constants
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRegistro
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRespuesta
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroModel {
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

    fun registrarUsuario(idUsuario: String, password: String, idTipoUsuario: String, callback: (Boolean, String) -> Unit) {
        apiService.registrarUsuario(action = "registrar", idUsuario = idUsuario, password = password, idTipoUsuario).enqueue(object :
            Callback<List<ClsDatosRespuesta>> {
            override fun onResponse(call: Call<List<ClsDatosRespuesta>>, response: Response<List<ClsDatosRespuesta>>)
            {
                if (response.isSuccessful) {
                    response.body()?.let { datos ->
                        if (datos[0].Estado == "true") {
                            callback(true, datos[0].Salida)
                        } else {
                            callback(false, "Ocurrió un error en el registro: " + datos[0].Salida)
                        }
                    } ?: run {
                        callback(false, "Respuesta vacía o en formato incorrecto")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    callback(false, "Error en la respuesta del servidor: $errorBody")
                }
            }
            override fun onFailure(call: Call<List<ClsDatosRespuesta>>, t: Throwable) {
                callback(false, "Error en la conexión: ${t.message}")
            }
        })
    }

    fun recuperarDatosUsuario(idUsuario: String, callback: (Boolean, String, List<ClsDatosRegistro>) -> Unit) {
        apiService.buscarUsuario(idUsuario).enqueue(object: Callback<List<ClsDatosRegistro>> {
            override fun onResponse(call: Call<List<ClsDatosRegistro>>, response: Response<List<ClsDatosRegistro>>) {
                if (response.isSuccessful) {
                    response.body()?.let { usuario ->
                        if (usuario.isNotEmpty()) {
                            if (!usuario.get(0).error.isEmpty()) {
                                callback(false, usuario[0].error, emptyList())
                            }
                            else {
                                callback(true, "Datos recuperados correctamente", usuario)
                            }
                        }
                        else {
                            callback(false, "No se encontró el usuario", emptyList())
                        }
                    } ?: callback(false, "Respuesta vacía del servidor", emptyList())
                }else {
                    Log.d("Error", "Error: ${response.message()}")
                    callback(false, "Error: ${response.message()}", emptyList())
                }
            }

            override fun onFailure(call: Call<List<ClsDatosRegistro>>, t: Throwable) {
                callback(false, "Error: ${t.message}", emptyList())
            }
        })
    }
}