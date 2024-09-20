package ru.grebe.moviescollection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.moviescollectoin.R
import ru.grebe.moviescollection.filmlist.fragment.FilmsListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        //toolbar init
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = com.example.moviescollectoin.filmsList.R.string.films.toString()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_root, FilmsListFragment()).commit()
        }


    }
}