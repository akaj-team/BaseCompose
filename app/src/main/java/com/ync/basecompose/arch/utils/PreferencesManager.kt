package com.ync.basecompose.arch.utils

import android.content.SharedPreferences
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@Module
@InstallIn(SingletonComponent::class)
class PreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val json: Json
) {
    private val editor = sharedPreferences.edit()

    /**
     * Delete all saved contents
     */
    fun clear() {
        editor.clear()
        editor.commit()
    }

    /**
     * Whether the value of the specified key is saved
     *
     * @param key
     */
    fun <Key : Enum<Key>> contains(key: Key): Boolean {
        return sharedPreferences.contains(key.name)
    }

    /**
     * Delete the value of the specified key
     *
     * @param key
     */
    fun <Key : Enum<Key>> remove(key: Key) {
        editor.remove(key.name)
        editor.commit()
    }

    /**
     * Save the value
     *
     * @param key
     * @param data
     */
    fun <Key : Enum<Key>> putString(key: Key, data: String) {
        editor.putString(key.name, data)
        editor.commit()
    }

    /**
     * Save the value
     *
     * @param key
     * @param data
     */
    fun <Key : Enum<Key>> putInt(key: Key, data: Int) {
        editor.putInt(key.name, data)
        editor.commit()
    }

    /**
     * Save the value
     *
     * @param key
     * @param data
     */
    fun <Key : Enum<Key>> putBoolean(key: Key, data: Boolean) {
        editor.putBoolean(key.name, data)
        editor.commit()
    }

    /**
     * Save the value
     *
     * @param key
     * @param data
     */
    fun <Key : Enum<Key>> putLong(key: Key, data: Long) {
        editor.putLong(key.name, data)
        editor.commit()
    }

    /**
     * Read value
     *
     * @param key
     * @param default Default value if no value corresponds to the key
     */
    fun <Key : Enum<Key>> getString(key: Key, default: String = ""): String {
        return sharedPreferences.getString(key.name, default) ?: default
    }

    /**
     * Read value
     *
     * @param key
     * @param default Default value if no value corresponds to the key
     */
    fun <Key : Enum<Key>> getInt(key: Key, default: Int = 0): Int {
        return sharedPreferences.getInt(key.name, default)
    }

    /**
     * Read value
     *
     * @param key
     * @param default Default value if no value corresponds to the key
     */
    fun <Key : Enum<Key>> getBoolean(key: Key, default: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key.name, default)
    }

    /**
     * Read value
     *
     * @param key
     * @param default Default value if no value corresponds to the key
     */
    fun <Key : Enum<Key>> getLong(key: Key, default: Long = 0): Long {
        return sharedPreferences.getLong(key.name, default)
    }

    /**
     * Save object value
     */
    fun <Key : Enum<Key>, T> putObject(key: Key, data: T, serializer: KSerializer<T>) {
        editor.putString(key.name, json.encodeToString(serializer, data))
        editor.commit()
    }

    /**
     * Read object value
     */
    fun <Key : Enum<Key>, T> getObject(key: Key, serializer: KSerializer<T>): T =
        json.decodeFromString(serializer, getString(key))
}