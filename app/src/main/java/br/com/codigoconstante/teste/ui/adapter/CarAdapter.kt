package br.com.codigoconstante.teste.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.codigoconstante.teste.R
import br.com.codigoconstante.teste.domain.Carro

class CarAdapter(private val carros: List<Carro>, private val isFavoriteScreen : Boolean = false)
    : RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    var carItemLister : (Carro) -> Unit = {}
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView
        val favorito: ImageView
        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                bateria = findViewById(R.id.tv_bateria_value)
                potencia = findViewById(R.id.tv_potencia_value)
                recarga = findViewById(R.id.tv_recarga_value)
                favorito = findViewById(R.id.iv_favorite)
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
            if(isFavoriteScreen) {
                favorito.setImageResource(R.drawable.ic_estrela_ativa_foreground)
            }
            favorito.setOnClickListener {
                val carro = carros[position]
                carItemLister(carro)
                setupFavorito(carro)
            }
        }
    }

    private fun ViewHolder.setupFavorito(carro: Carro) {
        carro.isFavorite = !carro.isFavorite
        if (carro.isFavorite) {
            favorito.setImageResource(R.drawable.ic_estrela_ativa_foreground)
        } else {
            favorito.setImageResource(R.drawable.ic_estrela_inativa_foreground)
        }
    }
}

