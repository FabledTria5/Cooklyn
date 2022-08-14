package dev.fabled.frameworks.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.applicationDataStore by preferencesDataStore(name = "Application_preferences")