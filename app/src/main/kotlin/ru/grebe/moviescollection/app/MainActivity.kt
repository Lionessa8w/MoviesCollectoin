package ru.grebe.moviescollection.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import ru.grebe.moviescollection.R
import com.google.android.material.snackbar.Snackbar
import ru.grebe.moviescollection.navigation.NavigationAction
import ru.grebe.moviescollection.navigation.NavigationHolder
import ru.grebe.moviescollection.app.navigation.NavigationHolderImpl
import ru.grebe.moviescollection.snackbar_holder.SnackBarHolder
import ru.grebe.moviescollection.toolbar_holder.ToolbarHolder

class MainActivity : AppCompatActivity(), NavigationHolder, ToolbarHolder, SnackBarHolder {

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

    override fun showErrorSnackBar(onClick: () -> Unit) {
        val snackBar = Snackbar.make(
            findViewById(R.id.container_root),
            R.string.text_error,
            Snackbar.LENGTH_LONG
        )
        val buttonText=R.string.text_replay

        snackBar.setAction(R.string.text_replay) { onClick() }
        snackBar.setDuration(Int.MAX_VALUE)
        snackBar.show()
    }
}