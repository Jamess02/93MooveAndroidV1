package com.example.a93m

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CoursUiState(
    val nom: String = "",
    val listeCours: List<CoursApi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class CoursViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CoursUiState())
    val uiState: StateFlow<CoursUiState> = _uiState.asStateFlow()

    init {
        fetchCours()
    }

    fun fetchCours() {
        viewModelScope.launch {
            _uiState.value = CoursUiState(isLoading = true)
            try {
                val cours = CoursRetrofit.retrofitService.getAllCours()
                android.util.Log.d("CoursViewModel", "Cours reçus: ${cours.size}")
                cours.forEach { android.util.Log.d("CoursViewModel", "Cours: ${it.nom}") }
                _uiState.value = CoursUiState(listeCours = cours)
            } catch (e: Exception) {
                android.util.Log.e("CoursViewModel", "Erreur: ${e.message}")
                _uiState.value = CoursUiState(error = e.message)
            }
        }
    }
}