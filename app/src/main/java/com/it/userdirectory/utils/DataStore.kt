package com.it.userdirectory.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.it.userdirectory.domain.model.users.UsersResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class DataStore(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")
        private val USER_KEY = stringPreferencesKey("user_item")
    }

    suspend fun saveUser(user: UsersResponseItem) {
        val json = Json.encodeToString(user)
        context.dataStore.edit { prefs ->
            prefs[USER_KEY] = json
        }
    }

    val getUser: Flow<UsersResponseItem?> = context.dataStore.data
        .map { prefs ->
            prefs[USER_KEY]?.let { json ->
                try {
                    Json.decodeFromString<UsersResponseItem>(json)
                } catch (e: Exception) {
                    null
                }
            }
        }

    suspend fun clearUser() {
        context.dataStore.edit { it.remove(USER_KEY) }
    }
}