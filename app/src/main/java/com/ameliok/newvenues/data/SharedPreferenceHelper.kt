package com.ameliok.newvenues.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(private val context: Context) {
    val SHARED_PREFS = "sharedPrefs"

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFS,
        Context.MODE_PRIVATE
    )



}