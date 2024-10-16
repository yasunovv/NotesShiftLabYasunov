package com.yasunov.notesshiftlabyasunov.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.yasunov.notesshiftlabyasunov.screen.editScreen.EditNoteDest
import com.yasunov.notesshiftlabyasunov.screen.editScreen.EditScreen
import com.yasunov.notesshiftlabyasunov.screen.mainScreen.MainScreen
import com.yasunov.notesshiftlabyasunov.screen.mainScreen.MainScreenDest

@Composable
fun ShiftNotesApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Box(modifier = modifier) {
        NavHost(navController = navController, startDestination = MainScreenDest) {
            composable<MainScreenDest> {
                MainScreen(onNoteClicked = { id ->
                    navController.navigate(EditNoteDest(id = id))
                })
            }
            composable<EditNoteDest> { backStackEntry ->
                val mainScreenDest: EditNoteDest = backStackEntry.toRoute()
                EditScreen(onBackClick = { navController.popBackStack() }, noteAddedListener = {
                    navController.popBackStack()
                }, id = mainScreenDest.id)
            }
        }

    }

}
