package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab13.ui.theme.Lab13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimateColorExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AnimateColorExample(modifier: Modifier = Modifier) {
    // 1. Define un estado para alternar el color.
    var isBlue by remember { mutableStateOf(true) }

    // 2. Anima el cambio de color con animateColorAsState.
    // Puedes experimentar con tween o spring como especificaciones de animación.
    val color by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 1000) // Cambia esto por spring() para experimentar
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        // 3. Botón para alternar el color del cuadro.
        Button(onClick = { isBlue = !isBlue }) {
            Text("Cambiar Color")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Cuadro con fondo animado que cambia de color.
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimateColorExamplePreview() {
    Lab13Theme {
        AnimateColorExample()
    }
}


