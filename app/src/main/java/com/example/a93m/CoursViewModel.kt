package com.example.a93m

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CoursUiState(
    val nom: String = "",
    val listeCours: List<String> = emptyList()
)

class CoursViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        CoursUiState(
            nom = "93Moove",
            listeCours = listOf("Vélo", "Piano", "Natation", "Karaté", "Hip Hop")
        )
    )
    val uiState: StateFlow<CoursUiState> = _uiState.asStateFlow()

    fun ajouterCours(nouveauCours: String) {
        val listeActuelle = _uiState.value.listeCours.toMutableList()
        listeActuelle.add(nouveauCours)
        _uiState.value = _uiState.value.copy(listeCours = listeActuelle)
    }
}