plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}
android {
    namespace = "ru.grebe.moviescollection.filmsList"
    buildFeatures {
        viewBinding=true
    }
}

dependencies {

    //glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //koin
    val koin_version = "4.0.0-RC2"
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-android:$koin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")

    implementation(project(":sourse:features:film_domain_api"))

    // utils
    implementation(project(":sourse:utils:snackbar_holder"))
}