package com.example.paisesapp_tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppPaises()
                }
            }
        }
    }
}

@Composable
fun AppPaises() {
    var listaPaises by remember { mutableStateOf<List<Pais>>(emptyList()) }
    var paisSeleccionado by remember { mutableStateOf<Pais?>(null) }
    var cargando by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            listaPaises = RetrofitClient.apiService.obtenerPaises()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cargando = false
        }
    }

    if (cargando) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (paisSeleccionado != null) {
        PantallaDetalle(pais = paisSeleccionado!!) {
            paisSeleccionado = null
        }
    } else {
        PantallaLista(paises = listaPaises) { pais ->
            paisSeleccionado = pais
        }
    }
}

@Composable
fun PantallaLista(paises: List<Pais>, alSeleccionarPais: (Pais) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Catálogo de Países",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(paises) { pais ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { alSeleccionarPais(pais) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = pais.banderas.imagenPng,
                            contentDescription = "Bandera",
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = pais.nombre.comun, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaDetalle(pais: Pais, alVolver: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = alVolver, modifier = Modifier.padding(bottom = 16.dp)) {
            Text("Volver a la lista")
        }

        AsyncImage(
            model = pais.banderas.imagenPng,
            contentDescription = "Bandera",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "1. Nombre Oficial: ${pais.nombre.oficial}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = "2. Nombre Común: ${pais.nombre.comun}")
        Text(text = "3. Capital: ${pais.capital?.joinToString() ?: "No tiene"}")
        Text(text = "4. Región: ${pais.region}")
        Text(text = "5. Subregión: ${pais.subregion ?: "N/A"}")
        Text(text = "6. Población: ${pais.poblacion} habitantes")
        Text(text = "7. Área: ${pais.area} km²")
        Text(text = "8. Continente: ${pais.continentes.joinToString()}")
        Text(text = "9. Inicio de semana: ${pais.inicioSemana.uppercase()}")
        Text(text = "10. Zona Horaria: ${pais.zonasHorarias.firstOrNull() ?: "N/A"}")
    }
}