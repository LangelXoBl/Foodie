package com.example.foodie.ui.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodie.ui.components.recipe.AddIngredientRow
import com.example.foodie.ui.components.recipe.IngredientList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel, onNavigateToForm: () -> Unit) {
    var titleInput = recipeViewModel.recipeTitle
    var descriptionInput = recipeViewModel.recipeDescription
    var imageInput = recipeViewModel.recipeImage
    var instructionsInput = recipeViewModel.recipeInstructions
    var preparationTimeInput = recipeViewModel.recipePreparationTime

    Scaffold(
        topBar = {
            Text(
                text = "Nueva receta",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    OutlinedTextField(value = titleInput, onValueChange = { titleInput=it}, label = { Text("Título") },
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(1f)
                    )}
                item {OutlinedTextField(value = descriptionInput, onValueChange = {descriptionInput = it}, label = { Text("Descripción") },
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(1f)
                    )}
                item {OutlinedTextField(value = imageInput, onValueChange = {imageInput = it}, label = { Text("Imagen") },
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(1f)
                    )}
                //item {OutlinedTextField(value = instructionsInput, onValueChange = {instructionsInput = it}, label = { Text("Instrucciones") },
                //        modifier = Modifier
                //            .padding(bottom = 8.dp)
                //            .fillMaxWidth(1f)
                //    )}
                item { OutlinedTextField(value = preparationTimeInput, onValueChange = {preparationTimeInput = it}, label = { Text("Tiempo de preparación") },
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(1f)
                    )}
                item { AddIngredientRow(recipeViewModel = recipeViewModel) }
                item { Spacer(Modifier.height(8.dp)) }
                item { IngredientList(recipeViewModel = recipeViewModel) }
                item { Spacer(Modifier.height(16.dp)) }
                item {
                    Button(onClick = onNavigateToForm) {
                        Text("Continuar")
                    }
                }
                item { Spacer(Modifier.height(16.dp)) } // Espacio adicional para facilitar el desplazamiento al último elemento
            }
        }
    )
}


@Composable
fun RecipeInstructionsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Formulario de receta")
    }
}
