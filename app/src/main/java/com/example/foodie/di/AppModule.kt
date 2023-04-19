package com.example.foodie.di

import com.example.foodie.data.remote.LoginDataSource
import com.example.foodie.data.remote.recipe.RecipeDataSource
import com.example.foodie.data.remote.recipe.RecipeService
import com.example.foodie.data.repository.RecipeRepository
import com.example.foodie.domain.FinderUseCase
import com.example.foodie.domain.recipe.RecipeUserCase
import com.example.foodie.ui.home.ListRecipesViewModel
import com.example.foodie.ui.recipe.RecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Injeccion con providers de librerias o interfaces
@Module //Le indicas que en un modulo de DI
@InstallIn(SingletonComponent::class) //Es el escope de hilt
class AppModule {

    @Singleton // crea una unica instancia de la clase
    @Provides // Prepara la clase para ser injectado
    fun provideReftrofit(): Retrofit{
        // https://foodie-api-production.up.railway.app/ <- URL de la API produccion
        return Retrofit.Builder()
            .baseUrl("https://foodie-api-production.up.railway.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginDateSource(retrofit: Retrofit): LoginDataSource{
        return retrofit.create(LoginDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeDataSource(retrofit: Retrofit): RecipeDataSource {
        return retrofit.create(RecipeDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideFinderUseCase(repository: RecipeRepository): FinderUseCase {
        return FinderUseCase(repository)
    }
    @Singleton
    @Provides
    fun provideRecipeRepository(recipeService: RecipeService): RecipeRepository {
        return RecipeRepository(recipeService)
    }

    @Singleton
    @Provides
    fun provideRecipeUseCase(recipeRepository: RecipeRepository): RecipeUserCase{
        return RecipeUserCase(recipeRepository)
    }

    @Singleton
    @Provides
    fun provideListRecipesViewModel(recipeUserCase: RecipeUserCase): ListRecipesViewModel{
        return ListRecipesViewModel(recipeUserCase)
    }
}