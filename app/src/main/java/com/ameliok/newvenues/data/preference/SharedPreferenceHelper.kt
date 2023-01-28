package com.ameliok.newvenues.data.preference

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(private val context: Context) {
    private val SHARED_PREFS = "sharedPrefs"

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFS,
        Context.MODE_PRIVATE
    )

    var favoriteVenuesIds : Set<String>
        get() = sharedPreferences.getStringSet(KEY_STATE, emptySet()) as Set<String>
        set(value) {
            sharedPreferences.edit().putStringSet(KEY_STATE,value).apply()
        }

    companion object {
        const val KEY_STATE = "pref_key_favourited_ids"
    }

}