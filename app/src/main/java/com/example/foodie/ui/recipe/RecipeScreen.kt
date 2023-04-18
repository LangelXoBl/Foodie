package com.example.foodie.ui.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.data.model.recipe.TextFieldAtributes
import com.example.foodie.ui.components.recipe.*

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel) {

    var titleInput = remember { mutableStateOf(TextFieldValue()) }
    var descriptionInput = remember { mutableStateOf(TextFieldValue()) }
    var imageInput = remember { mutableStateOf(TextFieldValue()) }
    var preparationTimeInput = remember { mutableStateOf(TextFieldValue()) }

    val attributes = listOf(
        TextFieldAtributes(value = titleInput.value, onValueChange = { titleInput.value = it }, label = "Título"),
        TextFieldAtributes(value = descriptionInput.value, onValueChange = { descriptionInput.value = it }, label = "Descripción"),
        TextFieldAtributes(value = imageInput.value, onValueChange = { imageInput.value = it }, label = "Imagen"),
        TextFieldAtributes(value = preparationTimeInput.value, onValueChange = { preparationTimeInput.value = it }, label = "Tiempo de preparación")
    )


    val customOutlinedTextFieldColors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
        textColor = Color.White,
        disabledTextColor = Color.Gray,
        backgroundColor = Color.DarkGray,
        cursorColor = Color.White,
        errorCursorColor = Color.Red,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.LightGray,
        disabledBorderColor = Color.DarkGray,
        errorBorderColor = Color.Red,
        leadingIconColor = Color.White,
        disabledLeadingIconColor = Color.Gray,
        errorLeadingIconColor = Color.Red,
        trailingIconColor = Color.White,
        disabledTrailingIconColor = Color.Gray,
        errorTrailingIconColor = Color.Red,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.LightGray,
        disabledLabelColor = Color.Gray,
        errorLabelColor = Color.Red,
        placeholderColor = Color.LightGray,
        disabledPlaceholderColor = Color.Gray
    )

    LaunchedEffect(
        key1 = recipeViewModel.selectedCategory,
        block = {
            recipeViewModel.getCategories()
        }
    )

    Scaffold(
        modifier = Modifier
        .background(color = Color(0xFF1E1E1E)),
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF1E1E1E))
                    .padding(16.dp)

            ) {
                attributes.forEach { attribute ->
                    item {
                        CustomOutlinedTextField(
                            value = attribute.value.text,
                            onValueChange = { attribute.onValueChange(TextFieldValue(it)) },
                            label = attribute.label,
                            customOutlinedTextFieldColors = customOutlinedTextFieldColors
                        )
                    }
                }
                item {
                    InputWithButton(
                        label = "Nuevo ingrediente",
                        buttonText = "Agregar",
                        icon = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.addIngredient),
                        onButtonClick = { recipeViewModel.addIngredient(it) },
                        customOutlinedTextFieldColors = customOutlinedTextFieldColors
                    )
                }
                item {
                    ExpandableContent(
                        title = "Ingredientes",
                        content = { IngredientList(recipeViewModel = recipeViewModel) }
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    InputWithButton(
                        label = "Nueva instrucción",
                        buttonText = "Agregar",
                        icon = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.addCategory),
                        onButtonClick = { recipeViewModel.addInstruction(it) },
                        customOutlinedTextFieldColors = customOutlinedTextFieldColors
                    )
                }
                item {
                    ExpandableContent(
                        title = "Instrucciones",
                    content = { InstructionsList(recipeViewModel = recipeViewModel) }
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp))}
                item {
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            ) {
                        GenDropdownMenu(
                            items = recipeViewModel.categories,
                            selectedItem = recipeViewModel.selectedCategory,
                            selectedItems = recipeViewModel.selectedCategories,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .weight(1f),
                            defaultLabel = "Selecciona una categoría",
                        )
                        IconButton(
                            onClick = { recipeViewModel.addCategory() }) {
                            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.addCategory), tint = Color.White)
                        }
                    }
                }
                item {
                    ExpandableContent(
                        title = "Categorías",
                        content = { CategoriesList(recipeViewModel = recipeViewModel) }
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                recipeViewModel.createRecipe(
                                    titleInput.value.text,
                                    descriptionInput.value.text,
                                    imageInput.value.text,
                                    preparationTimeInput.value.text
                                )},
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),

                        ) {
                            Text("Guardar receta", color = Color.White)
                        }
                    }
                }
                item {
                    Spacer(Modifier.height(48.dp))
                }
            }
        }
    )

}