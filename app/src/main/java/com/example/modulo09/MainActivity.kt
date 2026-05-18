package com.example.modulo09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.modulo09.data.RetrofitClient
import com.example.modulo09.ui.AmphibianHomeScreen
import com.example.modulo09.ui.theme.Modulo09Theme // Ajusta el nombre de tu tema si es diferente
import com.example.modulo09.viewmodel.AmphibianViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modulo09Theme { // Asegúrate de que use el nombre de tu tema por defecto

                // 1. Instanciamos el servicio único y lo pasamos al ViewModel
                val servicio = remember { RetrofitClient.apiService }
                val viewModel = remember { AmphibianViewModel(servicio) }

                // 2. Disparamos la petición a la API de Google de forma asíncrona al abrir la app
                LaunchedEffect(Unit) {
                    viewModel.obtenerAnfibios()
                }

                Scaffold { innerPadding ->
                    // 3. Pintamos la pantalla principal pasándole los estados observados
                    AmphibianHomeScreen(
                        lista = viewModel.listaAnfibios,
                        cargando = viewModel.estaCargando.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}