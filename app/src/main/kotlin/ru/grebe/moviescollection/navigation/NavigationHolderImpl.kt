package ru.grebe.moviescollection.navigation

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.moviescollectoin.R
import navigation.NavigationAction
import navigation.NavigationHolder
import ru.grebe.moviescollection.filmdetails.fragment.ARGUMENT_ID_KEY
import toolbar.ToolbarHolder

class NavigationHolderImpl(
    activity: Activity
) : NavigationHolder, ToolbarHolder {

    private val navController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    private val toolbar by lazy {
        activity.findViewById<Toolbar>(R.id.toolbar)
    }

    fun setUp() {
        toolbar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            /** не поняла как менять цвет, поэтому закостылила */
            toolbar.navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        }
    }

    override fun doNavigation(navigationAction: NavigationAction) {
        when (navigationAction) {
            is NavigationAction.OpenFilmDetailsFragment ->
                navController.navigate(
                    R.id.action_global_openFilmDetailFragment,
                    args = Bundle().apply {
                        putInt(ARGUMENT_ID_KEY, navigationAction.id)
                    })

            is NavigationAction.OpenFilmsListFragment ->
                navController.navigate(R.id.action_global_openFilmsListFragment)
        }
    }

    override fun changeToolbarTitle(newTitle: String) {
        toolbar.title = newTitle
    }

}