plugins { alias(libs.plugins.atgKotlinMultiplatform) }

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.data.storage.api)
                implementation(project.dependencies.platform(libs.di.koin.bom))
                implementation(libs.di.koin.core)
            }
        }
    }
}