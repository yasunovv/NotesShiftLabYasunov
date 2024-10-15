package com.yasunov.notesshiftlabyasunov

import android.app.Application
import com.yasunov.database.NoteDatabase
import com.yasunov.repository.NoteRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {
    @Inject
    lateinit var databse: NoteDatabase

    @Inject
    lateinit var repository: NoteRepository

}

