plugins { alias(libs.plugins.atgKotlinLibrary) }


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.network.api)
            implementation(projects.domain.account)
            implementation(project.dependencies.platform(libs.di.koin.bom))
            implementation(libs.di.koin.core)
        }
    }
}
