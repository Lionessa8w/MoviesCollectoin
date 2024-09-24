package ru.grebe.moviescollection.navigation

sealed class NavigationAction {


    class OpenFilmsListFragment() : NavigationAction()
    class OpenFilmDetailsFragment(val id: Int) : NavigationAction()
}