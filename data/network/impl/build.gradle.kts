plugins {
    alias(libs.plugins.kotlinxSerializationPlugin) version libs.versions.kotlin
    alias(libs.plugins.atgKotlinMultiplatform)
    alias(libs.plugins.googleKsp) version libs.versions.ksp.version
}

kotlin {

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.ios)
            }
        }
        val commonMain by getting {
            dependencies {
                api(projects.data.network.api)
                implementation(libs.bundles.network)
                implementation(libs.kotlinx.serialization)
                implementation(project.dependencies.platform(libs.di.koin.bom))
                implementation(libs.di.koin.core)
            }

        }

    }
}