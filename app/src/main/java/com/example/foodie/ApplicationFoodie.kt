package com.example.foodie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// En esta clase se inicializan las dependencias y se hace la injeccion con hilt
@HiltAndroidApp
class ApplicationFoodie: Application() {
}