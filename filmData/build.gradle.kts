plugins {
    id("feature-setup")
}


android {
    namespace = "com.example.moviescollectoin.filmData"
}
dependencies {
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")


    implementation ("androidx.room:room-runtime:2.6.1")


}

