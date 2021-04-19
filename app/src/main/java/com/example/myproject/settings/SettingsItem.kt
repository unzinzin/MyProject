package com.example.myproject.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class SettingsItem(val name: String, val onClick: (() -> Unit))