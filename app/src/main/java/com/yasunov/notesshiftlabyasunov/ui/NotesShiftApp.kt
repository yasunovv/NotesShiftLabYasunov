package com.yasunov.notesshiftlabyasunov.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.notesshiftlabyasunov.screen.MainScreen
import com.yasunov.notesshiftlabyasunov.screen.MainScreenDest
import kotlinx.serialization.Serializable

// todo разнести по фичам


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
                MainScreen()
            }
            composable<EditNoteDest> { backStackEntry ->

            }
        }
    }


}
