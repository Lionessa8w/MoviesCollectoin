package navigation

sealed class NavigationAction {


    class OpenFilmsListFragment() : NavigationAction()
    class OpenFilmDetailsFragment(val id: Int) : NavigationAction()
}