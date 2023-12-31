package com.example.testcompose.app.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcompose.data.models.Movies
import com.example.testcompose.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    private val movies = MutableLiveData<List<Movies>>()
    val moviesList = movies

    fun getMovies() {
        viewModelScope.launch {
            apiRepository.getAllMovies().let {
                if (it.isSuccessful) {
                    movies.value = it.body()
                } else {
                    Log.d("MyFilter", "Failed to load movies: ${it.errorBody()}")
                }
            }
        }
    }
}