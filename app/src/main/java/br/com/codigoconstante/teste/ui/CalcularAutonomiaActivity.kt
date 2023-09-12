package br.com.codigoconstante.teste.ui

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.codigoconstante.teste.R
import java.net.HttpURLConnection
import java.net.URL

class CalcularAutonomiaActivity : AppCompatActivity() {

    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var totalCalculado: TextView
    lateinit var btnFechar: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupView()
        setupListeners()
        setupCachePref()
    }

    private fun setupCachePref() {
        val valorCalculado = getSharedPref()
        totalCalculado.text = valorCalculado.toString()
    }

    private fun setupView() {
        preco = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_km_percorrido)
        totalCalculado = findViewById(R.id.tv_resultado)
        btnCalcular = findViewById(R.id.btn_calcular)
        btnFechar = findViewById(R.id.iv_close)
    }

    private fun setupListeners() {
        btnCalcular.setOnClickListener {
            totalCalculado.text = calcular(
                preco.text.toString().toFloat(),
                kmPercorrido.text.toString().toFloat()
            ).toString()
            totalCalculado.visibility = View.VISIBLE;
        }

        btnFechar.setOnClickListener {
            finish()
        }
    }

    private fun calcular(preco: Float, km: Float): Float {
        val resultado = preco / km
        saveSharedPref(resultado)
        return resultado
    }

    private fun saveSharedPref(resultado : Float) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.saved_calc), resultado)
            apply()
        }
    }

    private fun getSharedPref(): Float {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }
}