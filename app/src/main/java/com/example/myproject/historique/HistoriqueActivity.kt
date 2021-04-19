package com.example.myproject.historique

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myproject.databinding.ActivityHistoriqueBinding
import com.example.myproject.localisation.LocalPreferences
import com.example.myproject.settings.SettingsRecyclerView
import com.example.myproject.historique.HistoriqueAdapter

class HistoriqueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoriqueBinding
    companion object {
            fun getStartIntent(context: Context): Intent {
            return Intent(context, HistoriqueActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //créer un objet binding
        binding = ActivityHistoriqueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Création de la barre en haut de l'écran
        supportActionBar?.apply {
            setTitle("Historique")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        //Créations de l'array list items qui va accueillir les différentes positions contenues dans l'historique
        val items : Array<String> = LocalPreferences.getInstance(this).getHistory()!!.toTypedArray()


        binding.recyclerView.adapter= HistoriqueAdapter(items)

        //Binding du bouton delete qui permettra de supprimer l'historique
        binding.delbutton.setOnClickListener{
            LocalPreferences.getInstance(this).deleteHistory();
            finish()
        }

    }
    //Permet de retourner en arrière
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}