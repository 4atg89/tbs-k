import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

internal fun NamedDomainObjectContainer<KotlinSourceSet>.getOrCreate(name: String): KotlinSourceSet =
    getByName(name)

internal fun KotlinMultiplatformExtension.sourceSets(block: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit) {
    sourceSets.block()
}

internal fun Project.kotlin(configure: Action<KotlinMultiplatformExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlin", configure)

/**
 * Configures the [android][com.android.build.gradle.LibraryExtension] extension.
 */
fun Project.androidLibrary(configure: Action<com.android.build.gradle.LibraryExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("android", configure)