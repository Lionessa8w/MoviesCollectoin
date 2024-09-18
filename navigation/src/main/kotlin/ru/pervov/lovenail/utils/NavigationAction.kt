package ru.pervov.lovenail.utils

sealed class NavigationAction {

    // client
    class OpenClientList() : NavigationAction()
    class OpenCreateOrUpdateClient(val clientId: String?) : NavigationAction()

    // calendar
    class OpenWeekCalendar() : NavigationAction()
    class OpenDayCalendar() : NavigationAction()

    // procedure
    class OpenProcedureList() : NavigationAction()
    class OpenCreateOrUpdateProcedure(val procedureId: String?) : NavigationAction()

    // mock
    class OpenCreateMock() : NavigationAction()


}