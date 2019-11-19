package com.lucasmarciano.pwc.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreference main configuration.
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */
class Prefs(context: Context) {
    private val PREFS_FILENAME = "com.lucasmarciano.pwc.utils.prefs"
    private val STREET_NAME_PREF_KEY = "STREET_NAME_PREF_KEY"
    private val CITY_NAME_PREF_KEY = "CITY_NAME_PREF_KEY"
    private val STATE_NAME_PREF_KEY = "STATE_NAME_PREF_KEY"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var streetName: String?
        get() = prefs.getString(STREET_NAME_PREF_KEY, "")
        set(value) = prefs.edit().putString(STREET_NAME_PREF_KEY, value).apply()

    var cityName: String?
        get() = prefs.getString(CITY_NAME_PREF_KEY, "")
        set(value) = prefs.edit().putString(CITY_NAME_PREF_KEY, value).apply()

    var stateName: String?
        get() = prefs.getString(STATE_NAME_PREF_KEY, "")
        set(value) = prefs.edit().putString(STATE_NAME_PREF_KEY, value).apply()

    fun clearPrefs() {
        streetName = ""
        cityName = ""
        stateName = ""
    }
}