package com.example.myproject.main


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.historique.HistoriqueActivity
import com.example.myproject.localisation.LocalisationActivity
import com.example.myproject.settings.SettingsRecyclerView

//Création de la classe MainActivity qui sera la home de notre appli
class MainActivity : AppCompatActivity() {
    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Affiche un toast lorsque le main est lancé
        Toast.makeText(this, "Bienvenue sur l'application la Zinzinerie", Toast.LENGTH_LONG).show()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Binding du bouton rouage pour accéder à l'activity paramètres
        binding.rouage.setOnClickListener{
            startActivity(SettingsRecyclerView.getStartIntent(this));
        }

        //Binding du bouton melocaliser pour accéder à l'activity localisation
        binding.melocaliser.setOnClickListener{
            startActivity(LocalisationActivity.getStartIntent(this));
        }

        //Binding du bouton historique pour accéder à l'activity historique
        binding.historique.setOnClickListener{
            startActivity(HistoriqueActivity.getStartIntent(this));
        }
        }
    }
