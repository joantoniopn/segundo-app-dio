package br.com.codigoconstante.teste.ui

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
        return preco / km
    }
}