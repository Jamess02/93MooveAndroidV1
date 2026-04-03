package com.example.a93m.ui
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CoursViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CoursUiState())
    val uiState: StateFlow<CoursUiState> = _uiState

}


fun PageAccueil(modifier: Modifier=Modifier,
navController: NavHostController= rememberNavController()
){