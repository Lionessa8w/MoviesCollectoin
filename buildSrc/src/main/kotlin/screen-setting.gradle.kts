plugins {
    id("com.android.library")
}


android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.fragment:fragment-ktx:1.8.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation(project(":navigation"))
}
