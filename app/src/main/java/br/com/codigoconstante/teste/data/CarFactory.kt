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
            urlFoto = "www.google.com.br"
        ),
        Carro (
            id = 2,
            preco = "R$250.000,00",
            bateria = "250 kWh",
            potencia = "180cv",
            recarga = "23 min",
            urlFoto = "www.google.com.br"
        ),
        Carro (
            id = 3,
            preco = "R$285.000,00",
            bateria = "266 kWh",
            potencia = "175cv",
            recarga = "32 min",
            urlFoto = "www.google.com.br"
        )
    )
}