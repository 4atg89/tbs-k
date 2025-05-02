enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-common")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        val kotlinVersion = "2.0.20"
        val kspVersion = "2.0.20-1.0.24"
        val agpVersion = "8.9.2"
        val composeVersion = "1.6.11"

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        kotlin("plugin.compose").version(kotlinVersion)

        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)
        id("com.google.devtools.ksp").version(kspVersion)
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "tbs-k"
include(":androidApp")
include(":desktopApp")
include(":enter")