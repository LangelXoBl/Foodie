package com.example.foodie.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    if (details != null) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            LazyColumn {
                items(count = 1) {
                    RecipeImage(url = details.image, modifier = Modifier)
                    //Box(modifier = Modifier.padding(8.dp)){}
                    Text(
                        text = details.title,
                        style = TextStyle(
                            fontFamily = quicksandFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = details.description,
                        style = TextStyle(fontFamily = quicksandFontFamily)
                    )
                    IngredientsList(details.ingredients, context, quicksandFontFamily)
                    InstructionsList(instructions = details.instructions, quicksandFontFamily)
                }
            }


        }
    } else {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun IngredientsList(
    ingredients: List<IngredientResponse>,
    context: Context,
    quicksandFontFamily: FontFamily
) {
    Text(text = "Ingredientes", style = TextStyle(fontFamily = quicksandFontFamily, fontWeight = FontWeight.Bold))
    ingredients.map { ingredient ->
        Row() {
            Log.i("ingrediente", ingredient.name)
            Text(text = ingredient.name, style = TextStyle(fontFamily = quicksandFontFamily))
            Icon(
                Icons.Filled.Visibility,
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
    Text(text = "Instrucciones", style = TextStyle(fontFamily = quicksandFontFamily, fontWeight = FontWeight.Bold))
    instructions.forEach { instruction -> Text(text = instruction, style = TextStyle(fontFamily = quicksandFontFamily)) }
}