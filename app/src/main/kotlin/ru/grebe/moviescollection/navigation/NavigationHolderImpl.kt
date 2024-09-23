package ru.grebe.moviescollection.navigation

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.moviescollectoin.R
import navigation.NavigationAction
import navigation.NavigationHolder
import toolbar.ToolbarHolder

class NavigationHolderImpl (
    activity: Activity
) : NavigationHolder, ToolbarHolder {

    private val navController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment)
    }

    private val toolbar by lazy {
        activity.findViewById<Toolbar>(R.id.toolbar)
    }
    fun setUp(){
        toolbar.setupWithNavController(navController)

        toolbar.navigationIcon?.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)


    }

    override fun doNavigation(navigationAction: NavigationAction) {
        when(navigationAction){
            is NavigationAction.OpenFilmDetailsFragment ->
                navController.navigate(R.id.action_global_openFilmDetailFragment)
            is NavigationAction.OpenFilmsListFragment ->
                navController.navigate(R.id.action_global_openFilmsListFragment)
        }
    }

    override fun changeToolbarTitle(newTitle: String) {
        toolbar.title = newTitle
    }

}