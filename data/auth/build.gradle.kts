plugins { alias(libs.plugins.atgKotlinLibrary) }


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain.auth)
        }
    }
}
