package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
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
                    AnimateSizeAndPositionExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AnimateSizeAndPositionExample(modifier: Modifier = Modifier) {
    // 1. Definir estados para el tamaño y la posición.
    var size by remember { mutableStateOf(100.dp) }
    var offsetX by remember { mutableStateOf(0.dp) }
    var offsetY by remember { mutableStateOf(0.dp) }

    // 2. Animaciones de tamaño y desplazamiento.
    val animatedSize by animateDpAsState(targetValue = size, animationSpec = tween(durationMillis = 500))
    val animatedOffsetX by animateDpAsState(targetValue = offsetX, animationSpec = tween(durationMillis = 500))
    val animatedOffsetY by animateDpAsState(targetValue = offsetY, animationSpec = tween(durationMillis = 500))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        // 3. Botón que alterna el tamaño y la posición del cuadro.
        Button(onClick = {
            size = if (size == 100.dp) 150.dp else 100.dp
            offsetX = if (offsetX == 0.dp) 50.dp else 0.dp
            offsetY = if (offsetY == 0.dp) 50.dp else 0.dp
        }) {
            Text("Mover y Cambiar Tamaño")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Cuadro animado con tamaño y posición cambiantes.
        Box(
            modifier = Modifier
                .size(animatedSize)
                .offset(x = animatedOffsetX, y = animatedOffsetY)
                .background(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimateSizeAndPositionExamplePreview() {
    Lab13Theme {
        AnimateSizeAndPositionExample()
    }
}


