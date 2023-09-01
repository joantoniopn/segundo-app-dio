package br.com.codigoconstante.teste.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.codigoconstante.teste.R
import br.com.codigoconstante.teste.data.CarFactory
import br.com.codigoconstante.teste.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragment : Fragment() {

    lateinit var btnCalcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListeners()
    }

    private fun setupView(view: View) {
        view.apply{
            btnCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
        }

    }

    private fun setupList() {
        listaCarros.adapter = CarAdapter(CarFactory.list)
    }

    private fun setupListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(context,CalcularAutonomiaActivity::class.java))
        }
    }
}