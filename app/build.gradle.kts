plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "ru.grebe.moviescollection"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.grebe.moviescollection"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    val koin_version = "4.0.0-RC2"
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-android:$koin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")

    // utils
    implementation(project(":sourse:utils:snackbar_holder"))
    implementation(project(":sourse:utils:toolbar_holder"))


    // screens
    implementation(project(":sourse:features:film_details_screen"))
    implementation(project(":sourse:features:films_list_screen"))

    // module
    implementation(project(":sourse:features:film_data_api"))
    implementation(project(":sourse:features:film_domain_api"))
    implementation(project(":sourse:utils:navigation"))
}