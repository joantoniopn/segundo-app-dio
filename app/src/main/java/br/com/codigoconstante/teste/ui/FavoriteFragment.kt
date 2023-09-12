package br.com.codigoconstante.teste.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.codigoconstante.teste.R
import br.com.codigoconstante.teste.data.local.CarRepository
import br.com.codigoconstante.teste.domain.Carro
import br.com.codigoconstante.teste.ui.adapter.CarAdapter

class FavoriteFragment : Fragment() {
    lateinit var listaCarrosFavoritosView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    private fun setupView(view: View) {
        view.apply{
            listaCarrosFavoritosView = findViewById(R.id.rv_lista_carros_favoritos)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
    }

    private fun getCarsOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        return repository.getAll()
    }

    private fun setupList() {
        val carroAdapter = CarAdapter(getCarsOnLocalDb(), isFavoriteScreen = true)

        listaCarrosFavoritosView.apply {
            isVisible = true
            adapter = carroAdapter
        }

        carroAdapter.carItemLister = { carro ->
            //val carr = CarRepository(requireContext()).getAll(carro)
        }
    }


}