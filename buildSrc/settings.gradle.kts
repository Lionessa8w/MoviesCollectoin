dependencyResolutionManagement {
    // для использования version catalog в precompiled скриптах(buildSrc/src/main/kotlin)
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
