import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class AtgKotlinLibrary : Plugin<Project> {

    override fun apply(target: Project) {
        val libs = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
        target.pluginManager.apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)

        target.kotlin {
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
            }
        }

    }

    private val VersionCatalog.coroutinesCore
        get() = findLibrary("coroutines.core").get()

}
