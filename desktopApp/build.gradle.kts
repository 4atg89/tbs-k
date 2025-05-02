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
}

compose.desktop {
    application {
        mainClass = "desktopKt"
    }
}

kotlin {
    jvmToolchain(17)
}