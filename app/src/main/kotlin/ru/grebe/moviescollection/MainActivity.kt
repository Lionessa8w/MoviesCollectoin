package ru.grebe.moviescollection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviescollectoin.R
import navigation.NavigationAction
import navigation.NavigationHolder
import ru.grebe.moviescollection.navigation.NavigationHolderImpl


class MainActivity : AppCompatActivity(), NavigationHolder {

    private val navigationHolderImpl = NavigationHolderImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

//        //toolbar init
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        title = com.example.moviescollectoin.filmsList.R.string.films.toString()

//        if (savedInstanceState== null){
//            doNavigation(NavigationAction.OpenFilmsListFragment())
//        }

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container_root, FilmsListFragment()).commit()
//        }
    }

    override fun onResume() {
        super.onResume()
        navigationHolderImpl.setUp()
    }

    override fun doNavigation(navigationAction: NavigationAction) {
        navigationHolderImpl.doNavigation(navigationAction)
    }
}