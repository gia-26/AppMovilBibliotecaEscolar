package com.example.appbibliotecaescolar

import android.util.Log
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsLibros
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsPrestamos
import com.example.appbibliotecaescolar.Modelo.DataClass.ClsDatosRegistro
import com.example.appbibliotecaescolar.Modelo.ifaceApiService
import retrofit2.Call
import retrofit2.Callback
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Test {

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()

    private  val ApiService = retrofit.create(ifaceApiService::class.java)

    fun obtenerLibros() {
        ApiService.obtenerLibros().enqueue(object  : Callback<List<ClsLibros>> {

            override fun onResponse(
                call: Call<List<ClsLibros>>,
                response: Response<List<ClsLibros>>
            ) {
                Log.d("PRUEBA_RETROFIT", "LISTAR => ${response.body()}")
            }

            override fun  onFailure(call: Call<List<ClsLibros>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR => ${t.message}")
            }
        })
    }

    fun obtenerPrestamos(idUsuario : String) {
        ApiService.obtenerPrestamos(idUsuario).enqueue(object : Callback<List<ClsPrestamos>> {

            override fun onResponse(
                call: Call<List<ClsPrestamos>?>,
                response: Response<List<ClsPrestamos>?>
            ) {
                Log.d("PRUEBA_RETROFIT", "LISTAR => ${response.body()}")
            }

            override fun onFailure(call: Call<List<ClsPrestamos>?>, t: Throwable) {
                Log.e("PRUEBA RETROFIT", "ERROR => ${t.message}")
            }
        })
    }

    fun buscarUsuario(idUsuario: String) {
        ApiService.buscarUsuario(idUsuario).enqueue(object : Callback<List<ClsDatosRegistro>> {

            override fun onResponse(
                call: Call<List<ClsDatosRegistro>>,
                response: Response<List<ClsDatosRegistro>>
            ) {
                Log.d("PRUEBA_RETROFIT", "BUSCAR_USUARIO => ${response.body()}")
            }

            override fun onFailure(call: Call<List<ClsDatosRegistro>>, t: Throwable) {
                Log.e("PRUEBA_RETROFIT", "ERROR => ${t.message}")
            }
        })
    }
}
