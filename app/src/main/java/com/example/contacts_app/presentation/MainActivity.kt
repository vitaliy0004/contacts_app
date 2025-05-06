package com.example.contacts_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts_app.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        setContentView(R.layout.activity_main)
    }
}