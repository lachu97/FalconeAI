package com.example.falconeai.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("token_DB")

class TokenStorage(context: Context) {
    private val dataStore = context.dataStore
    companion object{
        val TOKEN = stringPreferencesKey("token")
    }
    val getToken:Flow<String> = dataStore.data.map {
        it[TOKEN] ?: ""
    }
   suspend fun storeToken(token:String){
       dataStore.edit {
           it[TOKEN] = token
       }
   }
}