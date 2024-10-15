package com.yasunov.notesshiftlabyasunov.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yasunov.designsystem.component.ShiftScaffold
import kotlinx.serialization.Serializable

// todo разнести по фичам
@Serializable
data object MainScreenDest

@Serializable
data object EditNoteDest

@Composable
fun ShiftNotesApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    ShiftScaffold { _ ->
        NavHost(navController = navController, startDestination = MainScreenDest) {
            composable<MainScreenDest> {

            }
            composable<EditNoteDest> { backStackEntry ->

            }
        }
    }


}
