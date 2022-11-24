package com.shalan.audioplayer.storage

import android.content.SharedPreferences
import androidx.core.content.edit


inline fun <reified T : Any> SharedPreferences.put(key: String, value: T) {
    this.edit {
        when (T::class) {
            Boolean::class -> putBoolean(key, value as Boolean)
            String::class -> putString(key, value as String)
            Long::class -> putLong(key, value as Long)
            Int::class -> putInt(key, value as Int)
            Float::class -> putFloat(key, value as Float)
            else -> TODO("Not implemented yet")
        }
    }
}

inline fun <reified T : Any> SharedPreferences.get(key: String, default: T? = null): T? =
    when (T::class) {
        Boolean::class -> getBoolean(key, default as? Boolean ?: false)
        String::class -> getString(key, default as? String)
        Long::class -> getLong(key, default as? Long? ?: 0L)
        Int::class -> getInt(key, default as? Int ?: 0)
        Float::class -> getFloat(key, default as? Float ?: 0f)
        else -> null
    } as T?

