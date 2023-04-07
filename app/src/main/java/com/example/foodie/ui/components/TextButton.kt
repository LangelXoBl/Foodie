package com.example.foodie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodie.R

@Composable
fun TextButton(text: String, onClick: () -> Unit) {
    val quicksandFontFamily = FontFamily(
        Font(R.font.quicksand, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.quicksand_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.quicksand_medium, FontWeight.Medium, FontStyle.Normal)
    )

    Column(
        modifier = Modifier.background(color = Color(0xFF141414))
    ) {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF40454F),
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(
                text = text, style = TextStyle(
                    fontFamily = quicksandFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            )
        }
    }
}