val kotlinRepoUrl: String? = providers.gradleProperty("kotlin_repo_url").orNull
val kotlinVersion: String? = providers.gradleProperty("kotlin_version").orNull

repositories {
  gradlePluginPortal()
  mavenCentral()
  if (!kotlinRepoUrl.isNullOrEmpty() && !kotlinVersion.isNullOrEmpty()) {
    exclusiveContent {
      forRepository { maven(kotlinRepoUrl) }
      filter { includeVersionByRegex("org.jetbrains.kotlin.*", ".*", kotlinVersion) }
    }
  }
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
