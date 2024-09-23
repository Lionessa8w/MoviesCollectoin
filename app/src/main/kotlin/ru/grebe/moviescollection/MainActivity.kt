package ru.grebe.moviescollection

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.moviescollectoin.R
import com.google.android.material.snackbar.Snackbar
import navigation.NavigationAction
import navigation.NavigationHolder
import ru.grebe.moviescollection.navigation.NavigationHolderImpl
import snackbar.SnackbarHolder
import toolbar.ToolbarHolder


class MainActivity : AppCompatActivity(), NavigationHolder, ToolbarHolder, SnackbarHolder {

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

    override fun showErrorSnackbar(view: View) {
        val snack= Snackbar.make(view, R.string.text_error, Snackbar.LENGTH_LONG)
        snack.show()
    }
}