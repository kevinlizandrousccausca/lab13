package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab13.ui.theme.Lab13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimatedContentExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample(modifier: Modifier = Modifier) {
    // 1. Define un estado para los diferentes estados de la pantalla.
    var currentState by remember { mutableStateOf("Cargando") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        // 2. Botones para cambiar el estado.
        Row {
            Button(onClick = { currentState = "Cargando" }) { Text("Cargando") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { currentState = "Contenido" }) { Text("Contenido") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { currentState = "Error" }) { Text("Error") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Uso de AnimatedContent para las transiciones entre estados.
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
            }
        ) { state ->
            when (state) {
                "Cargando" -> Text("Cargando...", fontSize = 24.sp)
                "Contenido" -> Text("Contenido listo", fontSize = 24.sp)
                "Error" -> Text("Error al cargar", fontSize = 24.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedContentExamplePreview() {
    Lab13Theme {
        AnimatedContentExample()
    }
}
