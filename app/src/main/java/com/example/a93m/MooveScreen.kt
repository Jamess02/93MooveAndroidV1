package com.example.a93m

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a93m.ui.CoursViewModel

enum class MooveScreen {
    Start,
    AfficherCours,
    Login
}

@Composable
fun MooveApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MooveScreen.Start.name,
        modifier = Modifier
    ) {
        composable(route = MooveScreen.Start.name) {
            PageAccueil(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }
        composable(route = MooveScreen.Login.name) {
            FormulaireLogin(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }
        composable(route = MooveScreen.AfficherCours.name) {
            ListeCours(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }
    }
}

@Composable
fun PageAccueil(
    modifier: Modifier = Modifier,
    coursViewModel: CoursViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val coursUiState by coursViewModel.uiState.collectAsState()

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Bienvenue sur 93Moove")
        Text(text = coursUiState.nom)

        Button(onClick = {
            navController.navigate(MooveScreen.AfficherCours.name) {
                popUpTo(MooveScreen.Start.name) { inclusive = false }
            }
        }) {
            Text("Consulter les cours")
        }

        Button(onClick = {
            navController.navigate(MooveScreen.Login.name) {
                popUpTo(MooveScreen.Start.name) { inclusive = false }
            }
        }) {
            Text("Se connecter")
        }
    }
}

@Composable
fun FormulaireLogin(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Page de connexion - à compléter")
    }
}

@Composable
fun ListeCours(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Liste des cours - à compléter")
    }
}
