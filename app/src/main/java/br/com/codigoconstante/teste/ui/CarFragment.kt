package br.com.codigoconstante.teste.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.codigoconstante.teste.R
import br.com.codigoconstante.teste.data.CarsApi
import br.com.codigoconstante.teste.data.local.CarRepository
import br.com.codigoconstante.teste.domain.Carro
import br.com.codigoconstante.teste.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarFragment : Fragment() {

    lateinit var btnCalcular: FloatingActionButton
    lateinit var listaCarrosView: RecyclerView
    lateinit var imagemSemInternet: ImageView
    lateinit var textoSemInternet: TextView
    lateinit var progress: ProgressBar
    lateinit var carsApi: CarsApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)){
            callService()
        } else emptyState()
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carsApi = retrofit.create(CarsApi::class.java)
    }

    private fun getAllCars() {
        carsApi.getAllCars().enqueue(object : Callback<List<Carro>> {
            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if(response.isSuccessful) {
                    response.body()?.let{
                        setupList(it)
                        return
                    }
                }
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupView(view: View) {
        view.apply{
            btnCalcular = findViewById(R.id.fab_calcular)
            listaCarrosView = findViewById(R.id.rv_lista_carros)
            progress = findViewById(R.id.pb_loader)
            imagemSemInternet = findViewById(R.id.iv_empty_state)
            textoSemInternet = findViewById(R.id.tv_no_internet)
        }
    }

    private fun setupList(lista: List<Carro>) {
        val carroAdapter = CarAdapter(lista)
        progress.visibility = View.GONE
        imagemSemInternet.visibility = View.GONE
        textoSemInternet.visibility = View.GONE

        listaCarrosView.apply {
            visibility = View.VISIBLE
            adapter = carroAdapter
        }
        carroAdapter.carItemLister = { carro ->
            val carr = CarRepository(requireContext()).saveIfNotExists(carro)
        }
    }

    private fun setupListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(context,CalcularAutonomiaActivity::class.java))
        }
    }

    private fun callService() {
        progress.visibility = View.VISIBLE
        getAllCars()
    }

    private fun checkForInternet(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network)?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun emptyState() {
        progress.isVisible = false
        listaCarrosView.isVisible = false
        imagemSemInternet.isVisible = true
        textoSemInternet.isVisible = true
    }
}