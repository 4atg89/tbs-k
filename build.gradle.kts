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
    kotlin("plugin.serialization") version "2.1.20"

    alias(libs.plugins.atgKotlinMultiplatform).apply(false)
    alias(libs.plugins.atgUiCompose) apply false
    alias(libs.plugins.atgKotlinLibrary) apply false
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
        compilerOptions {
            if (project.group.toString().equals("ui").not()) return@compilerOptions
            if (project.findProperty("composeCompilerReports") == "true") {
                freeCompilerArgs .addAll (
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                project.layout.buildDirectory.dir("compose_compiler_reports")
                                    .get().asFile.absolutePath
                    )
                )
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                freeCompilerArgs .addAll (
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                                project.layout.buildDirectory.dir("compose_compiler_metrics")
                                    .get().asFile.absolutePath
                    )
                )
            }
        }
    }
}
// paste to gradle command ->  ./gradlew assembleRelease -PcomposeCompilerReports=true -PcomposeCompilerMetrics=true
