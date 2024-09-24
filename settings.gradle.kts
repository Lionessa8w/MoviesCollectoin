pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MoviesCollectoin"
include(":app")

// features
include(":sourse:features:film_domain_api")
include(":sourse:features:film_data_api")

// screens
include(":sourse:features:film_details_screen")
include(":sourse:features:films_list_screen")

// utils
include(":sourse:utils:snackbar_holder")
include(":sourse:utils:toolbar_holder")
include(":sourse:utils:navigation")

