package com.example.moviescollectoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fragment.FilmsListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_root, FilmsListFragment()).commit()
        }


    }
}