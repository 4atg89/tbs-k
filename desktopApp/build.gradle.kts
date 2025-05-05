plugins {
    kotlin("jvm")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.enter)
    implementation(compose.desktop.currentOs)
    implementation(compose.ui)
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.components.resources)

    implementation(project.dependencies.platform(libs.di.koin.bom))
    implementation(libs.di.koin.core)
    implementation(libs.di.koin.compose)
}

compose.desktop {
    application {
        mainClass = "desktopKt"
    }
}

kotlin {
    jvmToolchain(17)
}