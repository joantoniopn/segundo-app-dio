package br.com.codigoconstante.teste.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.codigoconstante.teste.R
import br.com.codigoconstante.teste.domain.Carro

class CarAdapter(private val carros: List<Carro>)
    : RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView
        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                bateria = findViewById(R.id.tv_bateria_value)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_value)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false))
    }
    override fun getItemCount(): Int  = carros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            preco.text = carros[position].preco
            bateria.text = carros[position].bateria
            potencia.text = carros[position].potencia
            recarga.text = carros[position].recarga
        }
    }
}

