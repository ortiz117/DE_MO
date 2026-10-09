package com.example.dado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dado.ui.theme.DadoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DadoTheme(dynamicColor = false) {
                DiceRollerApp()
            }
        }
    }
}

private val diceImages = listOf(
    R.drawable.dice_1,
    R.drawable.dice_2,
    R.drawable.dice_3,
    R.drawable.dice_4,
    R.drawable.dice_5,
    R.drawable.dice_6
)

@Composable
fun DiceRollerApp() {
    var diceValue by remember { mutableIntStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }
    val history = remember { mutableStateListOf<Int>() }
    val scope = rememberCoroutineScope()
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isRolling) {
        if (!isRolling) return@LaunchedEffect

        launch {
            rotation.animateTo(
                targetValue = rotation.value + 720f,
                animationSpec = tween(durationMillis = 850, easing = FastOutSlowInEasing)
            )
        }

        launch {
            scale.animateTo(1.12f, tween(180))
            scale.animateTo(0.94f, tween(180))
            scale.animateTo(1.06f, tween(160))
            scale.animateTo(1f, tween(220))
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF101820)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF101820),
                            Color(0xFF1D2D35),
                            Color(0xFF2F4858)
                        )
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Lanza el dado",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Que caiga la suerte.",
                    color = Color(0xFFDDE8EA),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(34.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.94f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 14.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp, vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(230.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE9F3F1)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = diceImages[diceValue - 1]),
                                contentDescription = "Dado mostrando $diceValue",
                                modifier = Modifier
                                    .size(190.dp)
                                    .rotate(rotation.value)
                                    .scale(scale.value)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = if (isRolling) "Lanzando..." else "Resultado: $diceValue",
                            color = Color(0xFF1B2A2F),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = resultMessage(diceValue, isRolling),
                            color = Color(0xFF5D6D73),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(26.dp))
                        Button(
                            onClick = {
                                if (isRolling) return@Button

                                scope.launch {
                                    isRolling = true
                                    repeat(12) {
                                        diceValue = Random.nextInt(1, 7)
                                        delay(60)
                                    }

                                    val finalValue = Random.nextInt(1, 7)
                                    diceValue = finalValue
                                    history.add(0, finalValue)
                                    while (history.size > 5) {
                                        history.removeLast()
                                    }
                                    delay(120)
                                    isRolling = false
                                }
                            },
                            enabled = !isRolling,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE76F51),
                                contentColor = Color.White,
                                disabledContainerColor = Color(0xFFF2A38F),
                                disabledContentColor = Color.White
                            )
                        ) {
                            Text(
                                text = if (isRolling) "Rodando..." else "Lanzar dado",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))
                HistoryRow(history = history)
            }
        }
    }
}

@Composable
private fun HistoryRow(history: List<Int>) {
    if (history.isEmpty()) {
        Text(
            text = "Sin lanzamientos todavia.",
            color = Color(0xFFCFDCDF),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        return
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Ultimos lanzamientos",
            color = Color(0xFFCFDCDF),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            history.forEach { value ->
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.16f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = value.toString(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

private fun resultMessage(value: Int, rolling: Boolean): String {
    if (rolling) return "El dado esta girando."

    return when (value) {
        1 -> "Un inicio tranquilo, pero con estilo."
        6 -> "Tirada perfecta."
        else -> "Buena tirada. Puedes volver a intentar."
    }
}

@Preview(showBackground = true)
@Composable
private fun DiceRollerPreview() {
    DadoTheme(dynamicColor = false) {
        DiceRollerApp()
    }
}
