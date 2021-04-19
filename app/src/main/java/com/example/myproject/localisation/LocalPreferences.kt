package com.example.myproject.localisation

import android.content.Context
import android.content.SharedPreferences
import android.text.method.TextKeyListener.clear
import android.widget.Toast

class LocalPreferences private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    companion object {
        private var INSTANCE: LocalPreferences? = null

        fun getInstance(context: Context): LocalPreferences {
            return INSTANCE?.let {
                INSTANCE
            } ?: run {
                INSTANCE = LocalPreferences(context)
                return INSTANCE!!
            }
        }
    }

    //Fonction qui  permet d'ajouter une adresse à l'historique lorsqu'on l'appelle
    fun addToHistory(newEntry: String){
        val history = this.getHistory()
        history?.add(newEntry)
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }

    // Fonction qui permet de récupérer l'historique lorsqu'on l'appelle
    fun getHistory(): MutableSet<String>? {
        return sharedPreferences.getStringSet("histories",  mutableSetOf<String>().toMutableSet() )
    }

    // Fonction qui permet de supprimer totalement l'historique
    fun deleteHistory() {
        sharedPreferences.edit().clear().apply()
    }


}
