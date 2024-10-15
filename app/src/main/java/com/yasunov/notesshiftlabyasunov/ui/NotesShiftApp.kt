package com.yasunov.notesshiftlabyasunov.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        NavHost(navController = navController, startDestination = EditNoteDest) {
            composable<MainScreenDest> {
                MainScreen(onBackClick = {}, onSaveClick = { s: String, s1: String -> })
            }
            composable<EditNoteDest> { _ ->
                EditScreen(onBackClick = { navController.popBackStack() }, noteAddedListener = {
                    navController.popBackStack()
                })
            }
        }

    }

}
