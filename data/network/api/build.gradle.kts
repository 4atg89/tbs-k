plugins {
    alias(libs.plugins.kotlinxSerializationPlugin) version libs.versions.kotlin
    alias(libs.plugins.atgKotlinLibrary)
    alias(libs.plugins.googleKsp) version libs.versions.ksp.version
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization)
            }
        }
    }
}