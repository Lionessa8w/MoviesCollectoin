package ru.grebe.moviescollection.app.navigation

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.allViews
import androidx.core.view.size
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import ru.grebe.moviescollection.R
import ru.grebe.moviescollection.navigation.NavigationAction
import ru.grebe.moviescollection.navigation.NavigationHolder
import ru.grebe.moviescollection.film_details_screen.fragment.ARGUMENT_ID_KEY
import ru.grebe.moviescollection.toolbar_holder.ToolbarHolder

class NavigationHolderImpl(
    activity: Activity
) : NavigationHolder, ToolbarHolder {

    private val navController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    private val toolbar by lazy { activity.findViewById<Toolbar>(R.id.toolbar) }

    fun setUp() {
        toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, _, _ ->
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