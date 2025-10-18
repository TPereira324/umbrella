package pt.iade.ei.bestumbrella1.models

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "session")

class SessionManager(private val context: Context) {
    companion object {
        private val EMAIL_KEY = stringPreferencesKey("email")
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { prefs -> prefs[EMAIL_KEY] = email }
    }

    suspend fun getEmail(): String? {
        return context.dataStore.data.map { it[EMAIL_KEY] }.first()
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}