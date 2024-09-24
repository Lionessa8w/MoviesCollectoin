plugins {
    id("feature-setup")
}

android {
    namespace = "ru.grebe.moviescollection.filmDomain"
}
dependencies{

    //koin
    val koin_version = "4.0.0-RC2"
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-android:$koin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")

    implementation(project(":sourse:features:film_data_api"))
}