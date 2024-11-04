package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDpAsState
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
                    CombinedAnimationsExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CombinedAnimationsExample(modifier: Modifier = Modifier) {
    // 1. Estados para cambiar tamaño, color, visibilidad y modo claro/oscuro.
    var size by remember { mutableStateOf(100.dp) }
    var color by remember { mutableStateOf(Color.Blue) }
    var isVisible by remember { mutableStateOf(true) }
    var isDarkMode by remember { mutableStateOf(false) }

    // 2. Animaciones para tamaño y color del cuadro.
    val animatedSize by animateDpAsState(targetValue = size, animationSpec = tween(durationMillis = 500))
    val animatedColor by animateColorAsState(targetValue = color, animationSpec = tween(durationMillis = 500))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.Black else Color.White)
    ) {
        // 3. Botón para cambiar el tamaño y el color del cuadro.
        Button(onClick = {
            size = if (size == 100.dp) 150.dp else 100.dp
            color = if (color == Color.Blue) Color.Green else Color.Blue
        }) {
            Text("Cambiar Tamaño y Color")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Cuadro que cambia de tamaño y color animado.
        Box(
            modifier = Modifier
                .size(animatedSize)
                .background(animatedColor)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 5. Botón para alternar la visibilidad.
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Button(onClick = { isVisible = !isVisible }) {
                Text("Alternar Visibilidad")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 6. Botón para alternar entre modo claro y oscuro.
        Button(onClick = { isDarkMode = !isDarkMode }) {
            Text(if (isDarkMode) "Modo Claro" else "Modo Oscuro")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CombinedAnimationsExamplePreview() {
    Lab13Theme {
        CombinedAnimationsExample()
    }
}
