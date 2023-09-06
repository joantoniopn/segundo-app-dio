package br.com.codigoconstante.teste.data

import br.com.codigoconstante.teste.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {
    @GET("cars.json")
    fun getAllCars() : Call<List<Carro>>
}