package com.example.myproject.settings


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Adapter
import com.example.myproject.R
import com.example.myproject.databinding.ActivitySettingsRecyclerView2Binding
import com.example.myproject.settings.SettingsItem
import com.example.myproject.settings.AdapterSettings
//Création de la classe Paramètres
class SettingsRecyclerView : AppCompatActivity() {

    //Création d'une classe SettingsItem qui sera une arraylist comprenant tous les items contenus dans les paramètres
    data class SettingsItem(val name: String, val onClick: (() -> Unit))
    private lateinit var binding: ActivitySettingsRecyclerView2Binding

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, SettingsRecyclerView::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySettingsRecyclerView2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setTitle("Paramètres")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        //Création de l'array list comprenant les items paramètres
        val settings = arrayOf(
            SettingsItem("Paramètres applications ", { startActivity(Intent(Settings.ACTION_SETTINGS))
            }),
            SettingsItem("Parametres de localisation", { startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }),
            SettingsItem("Map ESEO", {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.492884574915365,-0.5509639806591626")))}),

            SettingsItem("Site de l'ESEO", {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://eseo.fr/")))}) ,

            SettingsItem("Me contacter", {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:antoinevallet@sfr.fr?Subject="+ Uri.encode("Contact depuis l'application")+ "&Body="+ Uri.encode("Bonjour, je vous contacte pour ..."))))
            }),

        )

        binding.recyclerView.adapter = AdapterSettings(settings)

    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}