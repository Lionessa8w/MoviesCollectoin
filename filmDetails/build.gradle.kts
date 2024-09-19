plugins {
    id("feature-setup")
    id("screen-setting")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.moviescollectoin.filmDetails"
}
dependencies{
    implementation ("com.github.bumptech.glide:glide:4.16.0")

//    implementation(project(":filmsData"))
//    implementation(project(":filmsDomain"))
}