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
import com.example.a93m.CoursViewModel
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Card
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    var login by remember { mutableStateOf("") }
    var motDePasse by remember { mutableStateOf("") }

    val backgroundColor = if (login.length >= 10) Color.Red else Color.White

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Connexion 93Moove",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            label = { Text("Login") },
            value = login,
            onValueChange = { login = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            label = { Text("Mot de passe") },
            value = motDePasse,
            onValueChange = { motDePasse = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { }) {
            Text("Valider")
        }
    }
}
@Composable
fun ListeCours(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coursViewModel: CoursViewModel = viewModel()
) {
    val uiState by coursViewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(uiState.listeCours) { cours ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(modifier = Modifier.padding(all = 16.dp)) {
                    Text(
                        text = cours,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
