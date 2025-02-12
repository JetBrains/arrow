val kotlin_repo_url: String? = project.findProperty("kotlin_repo_url") as? String

repositories {
  gradlePluginPortal()
  mavenCentral()
  kotlin_repo_url?.also { maven(it) }
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
