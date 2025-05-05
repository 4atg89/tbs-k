plugins {
    alias(libs.plugins.atgKotlinMultiplatform)
    alias(libs.plugins.atgUiCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.base)
            implementation(projects.domain.auth)
            implementation(projects.ui.auth)
            //todo remove from here
            implementation(projects.data.network.impl)
            implementation(projects.data.storage.impl)

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