plugins {
    alias(libs.plugins.atgKotlinMultiplatform)
    alias(libs.plugins.atgUiCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)
            implementation(libs.voyager.koin)
            implementation(project.dependencies.platform(libs.di.koin.bom))
            implementation(libs.bundles.di.kmp)
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.coroutines.android)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.coroutines.swing)
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}