buildscript {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.tools.build.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.composeCompiler).apply(false)
    alias(libs.plugins.jetbrainsCompose) apply false
    kotlin("plugin.serialization") version "2.0.20"

    alias(libs.plugins.atgKotlinMultiplatform).apply(false)
    alias(libs.plugins.atgUiCompose) apply false
}

subprojects {
    plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin> {
        configure<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension> {
            jvmToolchain(17)
        }
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            if (project.findProperty("composeCompilerReports") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                            project.buildDir.absolutePath + "/compose_compiler"
                )
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                            project.buildDir.absolutePath + "/compose_compiler"
                )
            }
        }
    }
}
// paste to gradle command -> ./gradlew assembleRelease -PcomposeCompilerReports=true
