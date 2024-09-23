package ru.grebe.moviescollection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviescollectoin.R
import navigation.NavigationAction
import navigation.NavigationHolder
import ru.grebe.moviescollection.navigation.NavigationHolderImpl
import toolbar.ToolbarHolder


class MainActivity : AppCompatActivity(), NavigationHolder, ToolbarHolder {

    private val navigationHolderImpl = NavigationHolderImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)
    }

    override fun onResume() {
        super.onResume()
        navigationHolderImpl.setUp()
    }

    override fun doNavigation(navigationAction: NavigationAction) {
        navigationHolderImpl.doNavigation(navigationAction)
    }

    override fun changeToolbarTitle(newTitle: String) {
        navigationHolderImpl.changeToolbarTitle(newTitle)
    }
}