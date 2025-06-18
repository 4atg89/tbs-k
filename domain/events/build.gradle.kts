plugins { alias(libs.plugins.atgKotlinLibrary) }

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project.dependencies.platform(libs.di.koin.bom))
                implementation(libs.di.koin.core)
                implementation(libs.kotlinx.datetime)
            }
        }
    }
}