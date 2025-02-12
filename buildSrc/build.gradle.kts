val kotlinRepoUrl: String? = providers.gradleProperty("kotlin_repo_url").orNull

repositories {
  gradlePluginPortal()
  mavenCentral()
  kotlinRepoUrl?.also { maven(it) }
  google()
}

plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(libs.gradlePlugin.kotlin.base)
  implementation(libs.gradlePlugin.kotlin.multiplatform)
  implementation(libs.gradlePlugin.android.kmp)
  implementation(libs.gradlePlugin.spotless)
  implementation(libs.gradlePlugin.animalSniffer)
  implementation(libs.gradlePlugin.kover)
  implementation(libs.gradlePlugin.dokka)
  implementation(libs.gradlePlugin.publish)
}
