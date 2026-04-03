package com.example.a93m

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private val CoursViewModel.uiState: Any

enum class mooveScreen() {
    Start,
    AfficherCours,
    Login
}
@Composable
fun mooveApp(){
    PageAccueil(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}
@Composable
fun PageAccueil(modifier: Modifier=Modifier,navController: NavController){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick={
            navController.navigate(route = MoovScreen.AfficherCours.name) {
                popUpTo(Moovscreen.Start.name) {
                    inclusive = false
                }
            }

            }){
            Text("Consulter les cours")
        }
    }
}
                coursViewModel: CoursViewModel= viewModel()
) {
    val coursViewModel = null
    val coursUiState by coursViewModel.uiState.collectAsState()
}

private fun Any.collectAsState() {
    TODO("Not yet implemented")
}

@Composable
fun mooveApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = mooveScreen.Start.name,
        modifier = Modifier
    ) {
        composable(route = mooveScreen.Start.name) {
            PageAccueil(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController=navController
            )
        }
        composable(route = mooveScreen.Login.name) {
            FormulaireLogin(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController=navController
            )
        }
        composable(route = mooveScreen.AfficherCours.name) {
            ListeCours(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController=navController
            )
        }
    }

