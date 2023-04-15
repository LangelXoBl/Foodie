package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodie.R

//@Preview()
@Composable()
fun CardRecipe() {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable { },
            elevation = 10.dp
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = "https://w6h5a5r4.rocketcdn.me/wp-content/uploads/2019/06/pizza-con-chorizo-jamon-y-queso-1080x671.jpg",
                    contentDescription ="Pizza",
                    modifier = Modifier
                        .size(150.dp)
                        .border(BorderStroke(1.dp, Color.Black))
                )
                /*Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Header",
                    modifier = Modifier
                        .size(150.dp)
                        .border(BorderStroke(1.dp, Color.Black))
                    //.background(Color.Yellow)
                )*/
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(
                        buildAnnotatedString {
                            append("welcome to ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W900,
                                    color = Color(0xFF4552B8)
                                )
                            ) {
                                append("Jetpack Compose Playground")
                            }
                        }
                    )
                    Text(
                        buildAnnotatedString {
                            append("Now you are in the ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                                append("Card")
                            }
                            append(" section")
                        }
                    )
                }
            }

        }
    }
}