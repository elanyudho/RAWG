package com.elanyudho.core.pref

import android.content.Context
import android.content.SharedPreferences
import com.elanyudho.core.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EncryptedPreferences
@Inject constructor(
    @ApplicationContext context: Context
) {
    private val sp : SharedPreferences by lazy {
        context.getSharedPreferences(SECURE_PREF_NAME, Context.MODE_PRIVATE)
    }

    private val spe : SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clear() {
        spe.clear().apply()
    }

    var encryptedToken: String
        get() = sp.getString(SECURE_SP_TOKEN, BuildConfig.TOKEN) ?: ""
        set(value) = spe.putString(SECURE_SP_TOKEN, value).apply()

    var baseUrl: String
        get() = sp.getString(BASE_URL, BuildConfig.BASE_URL) ?: ""
        set(value) = spe.putString(BASE_URL, value).apply()

    companion object {
        private const val SECURE_PREF_NAME = "com.elanyudho.rawg"
        private const val SECURE_SP_TOKEN = "pref_token"
        private const val BASE_URL = "base_url"
    }

}