plugins {
    alias(libs.plugins.atgKotlinMultiplatform)
    alias(libs.plugins.atgUiCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.base)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)
            implementation(libs.voyager.koin)

            implementation(project.dependencies.platform(libs.di.koin.bom))
            implementation(libs.bundles.di.kmp)

            implementation(libs.landscapist.coil3)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}