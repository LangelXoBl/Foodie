package com.example.foodie.ui.onboarding

import android.annotation.SuppressLint
import com.example.foodie.ui.components.TextButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.foodie.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
//@Preview()
fun Onboard(onboardViewModel: OnboardViewModel) {
    val quicksandFontFamily = FontFamily(
        Font(R.font.quicksand, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.quicksand_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.quicksand_medium, FontWeight.Medium, FontStyle.Normal)
    )
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.find_recipe))

    Scaffold() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = Color(0xFF141414))
                .fillMaxSize()
        ) {
            LottieAnimation(
                modifier = Modifier.size(320.dp),
                composition = composition,
            )
            Text(
                text = "Encuentra las mejores recetas", style = TextStyle(
                    fontFamily = quicksandFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                ), color = Color.White
            )
            Text(
                text = "Explora nuestra selección de deliciosas recetas y encuentra la inspiración para preparar tus comidas favoritas.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 16.dp),
                style = TextStyle(
                    fontFamily = quicksandFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                ),
                color = Color(0xFFBCBEC8)
            )
            TextButton(text = "Continuar") { onboardViewModel.finishOnboard() }
        }

    }
}
