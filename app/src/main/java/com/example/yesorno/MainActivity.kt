package com.example.yesorno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yesorno.ui.theme.YesOrNoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.graphicsLayer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YesOrNoTheme {
                YesOrNoScreen()
            }
        }
    }
}

@Composable
fun YesOrNoScreen() {

    var answer by remember {
        mutableStateOf("Задай вопрос")
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (answer) {

            "ДА" -> {
                Image(
                    painter = painterResource(id = R.drawable.yes),
                    contentDescription = "Да",
                    modifier = Modifier.size(220.dp),
                    contentScale = ContentScale.Fit
                )
            }

            "НЕТ" -> {
                Image(
                    painter = painterResource(id = R.drawable.no),
                    contentDescription = "Нет",
                    modifier = Modifier.size(220.dp),
                    contentScale = ContentScale.Fit
                )
            }

            else -> {
                Text(
                    text = answer,
                    fontSize = 25.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        val interactionSource = remember { MutableInteractionSource() }

        val isPressed by interactionSource.collectIsPressedAsState()

        val scale by animateFloatAsState(
            targetValue = if (isPressed) 0.96f else 1f,
            label = "buttonScale"
        )
        Button(
            onClick = {

                scope.launch {
                    answer = "думаю."
                    delay(1000)

                    answer = "нет, я не думаю.."
                    delay(1000)

                    answer = "а нет, думаю..."
                    delay(1000)

                    answer =
                        if (Random.nextBoolean())
                            "ДА"
                        else
                            "НЕТ"
                }
            },

            interactionSource = interactionSource,

            modifier = Modifier
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(35.dp)
                )
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale
                )
                .size(width = 220.dp, height = 70.dp),

            shape = RoundedCornerShape(35.dp),

            border = BorderStroke(
                width = 2.dp,
                color = Color.Black
            )

        ) {
            Text(
                text = "Получить ответ",
                fontSize = 22.sp
            )
        }
    }
}