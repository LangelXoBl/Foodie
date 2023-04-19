package com.example.foodie.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.ui.components.recipe.RecipeImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailRecipe(detailViewModel: DetailViewModel, id: Number?) {
    val context = LocalContext.current
    val quicksandFontFamily = FontFamily(
        Font(R.font.quicksand, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.quicksand_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.quicksand_medium, FontWeight.Medium, FontStyle.Normal)
    )

    val details = detailViewModel.detail.observeAsState(initial = null).value
    LaunchedEffect(key1 = id) {
        Log.i("id Recipe", "$id")
        detailViewModel.getDetail(id!!)
    }

    Scaffold(
        backgroundColor = Color(0xFF141414),
        topBar = {
            TopAppBar(
                title = { Text(text = details?.title ?: "", color = Color.White) },
                backgroundColor = Color(0xFF141414)
            )
        },
        content = {
            if (details != null) {
                LazyColumn {
                    items(count = 1) {
                        RecipeImage(url = details.image, modifier = Modifier.fillMaxWidth())
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = details.title,
                            style = TextStyle(
                                fontFamily = quicksandFontFamily,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                        Text(
                            text = details.description,
                            style = TextStyle(fontFamily = quicksandFontFamily),
                            color = Color(0xFFffffff)
                        )
                        IngredientsList(details.ingredients, context, quicksandFontFamily)
                        InstructionsList(instructions = details.instructions, quicksandFontFamily)
                    }
                }
            } else {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    )

}

@Composable
fun IngredientsList(
    ingredients: List<IngredientResponse>,
    context: Context,
    quicksandFontFamily: FontFamily
) {
    Text(
        text = "Ingredientes",
        style = TextStyle(fontFamily = quicksandFontFamily, fontWeight = FontWeight.Bold),
        color = Color.White
    )
    ingredients.map { ingredient ->
        Row() {
            Log.i("ingrediente", ingredient.name)
            Text(
                text = ingredient.name,
                style = TextStyle(fontFamily = quicksandFontFamily),
                color = Color.White
            )
            Icon(
                Icons.Filled.Visibility,
                tint = Color.White,
                contentDescription = "Ver ingrediente",
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ingredient.url))
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun InstructionsList(instructions: List<String>, quicksandFontFamily: FontFamily) {
    Text(
        text = "Instrucciones",
        style = TextStyle(fontFamily = quicksandFontFamily, fontWeight = FontWeight.Bold),
        color = Color.White
    )
    instructions.forEach { instruction ->
        Text(text = instruction,
            style = TextStyle(fontFamily = quicksandFontFamily),
            color = Color.White
        ) }
}