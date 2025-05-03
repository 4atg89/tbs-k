plugins {
    alias(libs.plugins.atgKotlinMultiplatform)
    alias(libs.plugins.atgUiCompose)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.common)
                implementation(libs.voyager.screenmodel)
            }
        }
    }
}
