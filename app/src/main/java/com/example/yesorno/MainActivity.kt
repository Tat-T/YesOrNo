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

        Text(
            text = answer,
            style = MaterialTheme.typography.headlineLarge,
            fontSize =
            if (answer == "ДА" || answer == "НЕТ")
                72.sp
            else
                22.sp,
            fontWeight =
                if (answer == "ДА" || answer == "НЕТ")
                    FontWeight.Bold
                else
                    FontWeight.Normal,
            color =
                when (answer) {
                    "ДА" -> Color(0xFF00C853)   // зелёный
                    "НЕТ" -> Color.Red          // красный
                    else -> Color.Black         // остальные сообщения
                }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                scope.launch {

                    // Стираем старый ответ
                    answer = "думаю."

                    // Ждём 1 секунду
                    delay(1000)

                    answer = "нет, я не думаю.."
                    delay(1000)

                    answer = "а нет, думаю..."
                    delay(1000)

                    // Показываем новый ответ
                    answer =
                        if (Random.nextBoolean())
                            "ДА"
                        else
                            "НЕТ"
                }
            }
        ) {
            Text("Получить ответ")
        }
    }
}