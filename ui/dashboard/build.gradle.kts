plugins {
    alias(libs.plugins.atgKotlinMultiplatform)
    alias(libs.plugins.atgUiCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.base)

            //todo https://voyager.adriel.cafe/navigation/multi-module-navigation/
            implementation(projects.ui.profile)
            implementation(projects.ui.market)
            implementation(projects.ui.heroes)
            implementation(projects.ui.home)
            implementation(projects.ui.clan)
            implementation(projects.ui.events)

            implementation(projects.domain.account)
            implementation(projects.data.account)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.tab.navigator)

            implementation(project.dependencies.platform(libs.di.koin.bom))
            implementation(libs.bundles.di.kmp)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}