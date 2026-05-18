package com.example.modulo09.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modulo09.data.AmphibianApiService
import com.example.modulo09.data.AmphibianModel
import kotlinx.coroutines.launch

class AmphibianViewModel(private val servicio: AmphibianApiService) : ViewModel() {

    val listaAnfibios = mutableStateListOf<AmphibianModel>()
    var estaCargando = mutableStateOf(false)

    fun obtenerAnfibios() {
        viewModelScope.launch {
            estaCargando.value = true
            try {
                val respuesta = servicio.getAmphibians()
                listaAnfibios.clear()
                listaAnfibios.addAll(respuesta)
            } catch (e: Exception) {
                // Control básico de errores
            } finally {
                estaCargando.value = false
            }
        }
    }
}