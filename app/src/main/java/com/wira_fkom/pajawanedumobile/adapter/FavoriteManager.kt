package com.wira_fkom.pajawanedumobile.adapter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wira_fkom.pajawanedumobile.data.Article

object FavoriteManager {
    private const val PREFS_NAME = "favorites_prefs"
    private const val FAVORITES_KEY = "favorites"

    fun addFavorite(context: Context, article: Article) {
        val favorites = getFavorites(context).toMutableList()
        favorites.add(article)
        saveFavorites(context, favorites)
    }

    fun removeFavorite(context: Context, article: Article) {
        val favorites = getFavorites(context).toMutableList()
        favorites.remove(article)
        saveFavorites(context, favorites)
    }

    fun getFavorites(context: Context): List<Article> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(FAVORITES_KEY, "")
        return if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<List<Article>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    private fun saveFavorites(context: Context, favorites: List<Article>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(favorites)
        editor.putString(FAVORITES_KEY, json)
        editor.apply()
    }

    fun isFavorite(context: Context, article: Article): Boolean {
        return getFavorites(context).contains(article)
    }
}
