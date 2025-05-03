plugins {
    `kotlin-dsl`
}

group = "com.atg.tbs-k.buildcommon"

dependencies {
    compileOnly(libs.android.tools.build.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.org.jetbrains.compose.gradle.plugin)
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {

        register("atgUiCompose") {
            id = "atg.ui.compose"
            implementationClass = "AtgComposePlugin"
        }

        register("atgKotlinMultiplatform") {
            id = "atg.kotlin.multiplatform"
            implementationClass = "AtgMultiplatformPlugin"
        }
        register("atgKotlinLibrary") {
            id = "atg.kotlin.library"
            implementationClass = "AtgKotlinLibrary"
        }
    }
}
