package com.example.foodie.ui.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodie.R
import com.example.foodie.ui.components.TextButton


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState("")

    val scaffoldState = rememberScaffoldState(
        snackbarHostState = SnackbarHostState()
    )

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            Modifier
                .background(color = Color(0xFF141414))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (isLoading) {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                Login(Modifier.align(Alignment.Center), viewModel)
            }
        }

        LaunchedEffect(errorMessage) {
            if (errorMessage.isNotEmpty()) {
                scaffoldState.snackbarHostState.showSnackbar(errorMessage)
            }
        }
    }
}




@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {
    val username: String by viewModel.username.observeAsState(initial = "Jose")
    val password: String by viewModel.password.observeAsState(initial = "password")
    val valid: Boolean by viewModel.valid.observeAsState(initial = false)

    Column(modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        UsernameField(username = username) { viewModel.onLoginChanged(it, password) }
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField(password) { viewModel.onLoginChanged(username, it) }
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(valid) { viewModel.onLoginSelected() }
        Spacer(modifier = Modifier.padding(8.dp))
        Options { viewModel.anonymous() }
    }
}

@Composable
fun Options(anonymous: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        TextButton(text = "Registrarse") {

        }
        TextButton(text = "Invitado") { anonymous() }
    }
}

@Composable
fun LoginButton(valid: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xffff4303),
            disabledBackgroundColor = Color(0xfff78058),
            contentColor = Color.White
        ), enabled = valid
    ) {
        Text(text = "Iniciar sesión")
    }
}


@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "¿Olvidaste tu correo o contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xfff89600)

    )
}


@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = Color(0xffdedddd),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val image = if (showPassword) Icons.Filled.VisibilityOff
            else Icons.Filled.Visibility
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(imageVector = image, contentDescription = "mostrar constraseña")
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}


@Composable
fun UsernameField(username: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = username,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Username") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = Color(0xffdedddd),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )

    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Header",
        modifier = modifier
    )
}