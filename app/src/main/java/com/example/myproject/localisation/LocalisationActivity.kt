package com.example.myproject.localisation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myproject.R
import com.example.myproject.databinding.ActivityLocalisationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.*

//Création de la classe LocalisationActivity
class LocalisationActivity : AppCompatActivity() {


    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LocalisationActivity::class.java)
        }
    }

    val PERMISSION_REQUEST_LOCATION = 9999

    private lateinit var binding: ActivityLocalisationBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localisation)

        binding = ActivityLocalisationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setTitle("Ma localisation ?")
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowCustomEnabled(true)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.malocalisation.setOnClickListener{

            requestPermission()
        }
    }

    //  Fonction qui permet de calculer la distance de notre localisation vers l'eseo avec un arrondi
    private fun calculDistance(localisation: Location) {

        val arrondi = Math.pow(10.0, 1.0) //variable pour arrondir
        val eseo = Location("localisation eseo")
        eseo.latitude = 47.4937187
        eseo.longitude = -0.5504861
        val distanceEseo = localisation.distanceTo(eseo).toDouble()/1000;

        val distanceEseoArrondi = Math.floor((distanceEseo)*arrondi)/arrondi;
        binding.distance.setText(distanceEseoArrondi.toString()+ " km");
    }

    //Fonction qui permet d'appeler la fonction getLocation si on a les permissions
    private fun requestPermission() {
        if (!hasPermission()) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_LOCATION
            )
        } else {

            getLocation()
        }
    }

    //Fonction qui permet de sauvegarder dans l'historique la localisation actuelle
    private fun savePosition(adresse : String){
        //LocalPreferences.getInstance(this).saveStringValue(adresse)
        LocalPreferences.getInstance(this).addToHistory(adresse)
    }

    //Fonction qui permet de savoir si on a les permissions ou pas (vrai ou faux)
    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission obtenue, Nous continuons la suite de la logique.
                    getLocation()
                } else {
                    // TODO
                    // Permission non accepté, expliqué ici via une activité ou une dialog pourquoi nous avons besoin de la permission
                }
                return
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {                   // Activation Bouton Retour
        finish()
        return true
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (hasPermission()) {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token)
                .addOnSuccessListener {
                    calculDistance(it)
                    geoCode(it)


                }
                .addOnFailureListener {
                    // Remplacer par un vrai bon message
                    Toast.makeText(this, "Localisation impossible", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun geoCode(location: Location){
        val geocoder = Geocoder(this, Locale.getDefault())
        val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        if (results.isNotEmpty()) {
            binding.locationText.text = results[0].getAddressLine(0)
        }
        Log.d("localisation",results[0].getAddressLine(0))
        savePosition(results[0].getAddressLine(0))
    }



}

