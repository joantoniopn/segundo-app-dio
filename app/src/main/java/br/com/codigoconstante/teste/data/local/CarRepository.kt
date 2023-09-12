package br.com.codigoconstante.teste.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.codigoconstante.teste.data.local.CarrosContract.CarEntry.COLUMN_NAME_BATERIA
import br.com.codigoconstante.teste.data.local.CarrosContract.CarEntry.COLUMN_NAME_CAR_ID
import br.com.codigoconstante.teste.data.local.CarrosContract.CarEntry.COLUMN_NAME_POTENCIA
import br.com.codigoconstante.teste.data.local.CarrosContract.CarEntry.COLUMN_NAME_PRECO
import br.com.codigoconstante.teste.data.local.CarrosContract.CarEntry.COLUMN_NAME_RECARGA
import br.com.codigoconstante.teste.data.local.CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO
import br.com.codigoconstante.teste.domain.Carro

class CarRepository(private val context: Context) {
    private fun save(carro: Carro): Boolean {
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NAME_CAR_ID, carro.id)
                put(COLUMN_NAME_PRECO, carro.preco)
                put(COLUMN_NAME_BATERIA, carro.bateria)
                put(COLUMN_NAME_RECARGA, carro.recarga)
                put(COLUMN_NAME_POTENCIA, carro.potencia)
                put(COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
            }

            return db?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values) != null

        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao Inserir ->", it)
            }
            return false
        }
    }

    fun findCarById(id: Int): Carro {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_URL_PHOTO
        )
        val filtro = "$COLUMN_NAME_CAR_ID = ?"
        val filterValues = arrayOf(id.toString())
        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME,
            columns,
            filtro,
            filterValues,
            null,
            null,
            null
        )

        var itemId : Long = 0
        var preco : String = ""
        var bateria : String = ""
        var potencia : String = ""
        var recarga : String = ""
        var urlPhoto : String = ""
        var isFavorite : Boolean = false

        with(cursor) {
            while(moveToNext()) {
                itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                bateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA))
                potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                recarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA))
                urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))
                isFavorite = true
            }
        }
        cursor.close()
        return Carro(
            id = itemId.toInt(),
            preco = preco,
            bateria = bateria,
            potencia = potencia,
            recarga = recarga,
            urlPhoto = urlPhoto,
            isFavorite = isFavorite
        )
    }

    fun saveIfNotExists(carro: Carro) {
        val car = findCarById(carro.id)
        if(car.id == ID_WHEN_NO_CAR) {
            save(carro)
        }
    }

    fun getAll(): List<Carro> {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_URL_PHOTO
        )

        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val carro = mutableListOf<Carro>()

        with(cursor) {
            while(moveToNext()) {
                carro.add(Carro(
                    id = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID)).toInt(),
                    preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO)),
                    bateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA)),
                    potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA)),
                    recarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA)),
                    urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO)),
                    isFavorite = true
                ))
            }
        }
        cursor.close()
        return carro
    }

    companion object {
        const val ID_WHEN_NO_CAR = 0
    }
}