package com.example.a93m
import androidx.compose.runtime.mutableStateListOf
import  androidx.lifecycle.ViewModel
class CoursViewModel : ViewModel(){
    var cours = mutableStateListOf("vélo", "piano", "natation")
        private set

    fun ajouterCours(nouveauCours: String) {
        cours.add(nouveauCours)
    }

}
}
