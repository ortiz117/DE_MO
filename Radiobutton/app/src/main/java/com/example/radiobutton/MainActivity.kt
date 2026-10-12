package com.example.radiobutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.radiobutton.ui.theme.RadiobuttonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadiobuttonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameZoneScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameZoneScreen(modifier: Modifier = Modifier) {
    var modoOscuro by remember { mutableStateOf(false) }
    var musica by remember { mutableStateOf(true) }
    var dificultad by remember { mutableStateOf("Normal") }

    val backgroundColor = if (modoOscuro) {
        Color(0xFF20183D)
    } else {
        MaterialTheme.colorScheme.background
    }
    val contentColor = if (modoOscuro) {
        Color(0xFFF5F0FF)
    } else {
        MaterialTheme.colorScheme.onBackground
    }
    val cardColor = if (modoOscuro) {
        Color(0xFF3A2E63)
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "GameZone",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Personaliza tu experiencia",
                style = MaterialTheme.typography.bodyLarge
            )

            HorizontalDivider(color = contentColor.copy(alpha = 0.25f))

            Text(
                text = "Preferencias del juego",
                style = MaterialTheme.typography.titleLarge
            )

            PreferenceSwitchRow(
                title = "Modo oscuro",
                description = "Cambia la apariencia",
                checked = modoOscuro,
                onCheckedChange = { modoOscuro = it }
            )

            PreferenceSwitchRow(
                title = "Musica del juego",
                description = "Activa la banda sonora",
                checked = musica,
                onCheckedChange = { musica = it }
            )

            HorizontalDivider(color = contentColor.copy(alpha = 0.25f))

            Text(
                text = "Nivel de dificultad",
                style = MaterialTheme.typography.titleLarge
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    listOf("Facil", "Normal", "Experto").forEach { nivel ->
                        DifficultyOption(
                            text = nivel,
                            selected = dificultad == nivel,
                            onClick = { dificultad = nivel }
                        )
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Tu configuracion",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(text = "Modo oscuro: ${if (modoOscuro) "Activado" else "Desactivado"}")
                    Text(text = "Musica: ${if (musica) "Activada" else "Desactivada"}")
                    Text(text = "Dificultad: $dificultad")
                }
            }
        }
    }
}

@Composable
private fun PreferenceSwitchRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun DifficultyOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .heightIn(min = 48.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameZonePreview() {
    RadiobuttonTheme {
        GameZoneScreen()
    }
}
