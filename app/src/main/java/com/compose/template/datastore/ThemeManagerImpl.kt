package com.compose.template.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val forceDarkModeKey = booleanPreferencesKey("theme")
    val state = MutableLiveData<Boolean?>(null)

    fun request() {
        viewModelScope.launch {
            dataStore.data.collectLatest {
                state.value = it[forceDarkModeKey]
            }
        }
    }

    fun switchToUseDarkMode(isDarkTheme: Boolean) {
        viewModelScope.launch {
            dataStore.edit {
                it[forceDarkModeKey] = isDarkTheme
            }
        }
    }
}