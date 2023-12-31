package br.com.codigoconstante.teste.data

import br.com.codigoconstante.teste.domain.Carro

object CarFactory {
    val list = listOf(
        Carro (
            id = 1,
            preco = "R$300.000,00",
            bateria = "300 kWh",
            potencia = "200cv",
            recarga = "30 min",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        ),
        Carro (
            id = 2,
            preco = "R$250.000,00",
            bateria = "250 kWh",
            potencia = "180cv",
            recarga = "23 min",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        ),
        Carro (
            id = 3,
            preco = "R$285.000,00",
            bateria = "266 kWh",
            potencia = "175cv",
            recarga = "32 min",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        ),
        Carro (
            id = 4,
            preco = "R$200.000,00",
            bateria = "250 kWh",
            potencia = "150cv",
            recarga = "42 min",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        ),
        Carro (
            id = 5,
            preco = "R$150.000,00",
            bateria = "100 kWh",
            potencia = "150cv",
            recarga = "21 min",
            urlPhoto = "www.google.com.br",
            isFavorite = false
        )
    )
}