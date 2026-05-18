package com.example.modulo09.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.modulo09.data.AmphibianModel

@Composable
fun AmphibianHomeScreen(
    lista: List<AmphibianModel>,
    cargando: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (cargando) {
            // Muestra una ruedita de carga si la API sigue descargando datos
            CircularProgressIndicator()
        } else if (lista.isEmpty()) {
            Text(text = "No se encontraron anfibios.")
        } else {
            // Lista optimizada para hacer scroll con las tarjetas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(lista) { anfibio ->
                    AmphibianCard(anfibio = anfibio)
                }
            }
        }
    }
}

@Composable
fun AmphibianCard(anfibio: AmphibianModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Título principal con Nombre y Tipo
            Text(
                text = "${anfibio.name} (${anfibio.type})",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Componente de la librería Coil que descarga y procesa la URL de la foto automáticamente
            AsyncImage(
                model = anfibio.imgSrc,
                contentDescription = anfibio.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción detallada del Codelab
            Text(
                text = anfibio.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}