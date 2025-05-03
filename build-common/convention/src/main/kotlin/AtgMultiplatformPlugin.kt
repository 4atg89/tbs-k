import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

@ExperimentalKotlinGradlePluginApi
class AtgMultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val libs = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
        target.pluginManager.apply {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("androidLibrary").get().get().pluginId)
        }

        target.androidLibrary {
            namespace = "com.atg.${target.name}"
            compileSdk = 35
            defaultConfig { minSdk = 28 }
        }

        target.kotlin {

            applyDefaultHierarchyTemplate()

            androidTarget {}
            jvm()
            listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
                iosTarget.binaries.framework { baseName = target.name }
            }
            sourceSets {
                commonMain {
                    dependencies {
                        implementation(libs.coroutinesCore)
                    }
                }
                androidMain {
                    dependencies {
                        implementation(libs.coroutinesAndroid)
                    }
                }
                jvmMain {
                    dependencies {
                        implementation(libs.coroutinesSwing)
                    }
                }
            }
        }
    }
}

private val VersionCatalog.coroutinesAndroid
    get() = findLibrary("coroutines.android").get()

private val VersionCatalog.coroutinesCore
    get() = findLibrary("coroutines.core").get()

private val VersionCatalog.coroutinesSwing
    get() = findLibrary("coroutines.swing").get()