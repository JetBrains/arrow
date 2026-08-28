import kotlinx.knit.KnitPluginExtension
import org.gradle.internal.classpath.Instrumented.systemProperty

allprojects {
  val version = (property("version") as? String).let { version ->
    if (version == null || version == "unspecified") "2.2.0-SNAPSHOT"
    else version
  }
  setProperty("version", version)
  systemProperty("arrowVersion", version)
}

buildscript {
  /*
  val kotlinRepoUrl: String? = providers.gradleProperty("kotlin_repo_url").orNull
  val kotlinVersion: String? = providers.gradleProperty("kotlin_version").orNull
  repositories {
    mavenCentral()
    if (!kotlinRepoUrl.isNullOrEmpty() && !kotlinVersion.isNullOrEmpty()) {
      exclusiveContent {
        forRepository { maven(kotlinRepoUrl) }
        filter { includeVersionByRegex("org.jetbrains.kotlin.*", ".*", kotlinVersion) }
      }
    }
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
  */

  dependencies {
    classpath(libs.kotlinx.knit)
  }
}

plugins {
  base
  id(libs.plugins.kotlin.multiplatform.get().pluginId) apply false
  id(libs.plugins.android.kmp.get().pluginId) apply false
  id(libs.plugins.spotless.get().pluginId) apply false
  id(libs.plugins.animalSniffer.get().pluginId) apply false
  id(libs.plugins.dokka.get().pluginId)
  // alias(libs.plugins.kotlinx.serialization) apply false
  id(libs.plugins.publish.get().pluginId) apply false
  // alias(libs.plugins.compose.jetbrains) apply false
  // alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.kotlinx.knit)
  id(libs.plugins.kotlinx.kover.get().pluginId)
}

configure<KnitPluginExtension> {
  siteRoot = "https://arrow-kt.io/"
  rootDir = file("arrow-libs")
  files = fileTree(file("arrow-libs")) {
    include("**/*.md")
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/build/**")
    exclude("**/.gradle/**")
    exclude("**/vibe/**")
  }
}

tasks.getByName("knitPrepare") {
  dependsOn(tasks.dokkaGenerate)
}

dependencies {
  kover(projects.arrowAtomic)
  kover(projects.arrowAutoclose)
  kover(projects.arrowCore)
  kover(projects.arrowCoreHighArity)
  kover(projects.arrowCache4k)
  kover(projects.arrowFunctions)
  kover(projects.arrowFxCoroutines)
  kover(projects.arrowFxStm)
  kover(projects.arrowOptics)
  kover(projects.arrowOpticsReflect)
  // kover(projects.arrowOpticsCompose) // disabled for Kotlin Compatibility Testing
  kover(projects.arrowResilience)
  kover(projects.arrowCollectors)
  kover(projects.arrowEval)
  // kover(projects.arrowCoreJackson) // disabled for Kotlin Compatibility Testing
  // kover(projects.arrowCoreJackson2) // disabled for Kotlin Compatibility Testing
  // kover(projects.arrowCoreRetrofit) // disabled for Kotlin Compatibility Testing
  // kover(projects.arrowCoreSerialization) // disabled for Kotlin Compatibility Testing
  kover(projects.arrowResilienceKtorClient) // disabled for Kotlin Compatibility Testing
  // kover(projects.arrowRaiseKtorServer) // disabled for Kotlin Compatibility Testing
  // kover(projects.arrowRaiseKtorServerResources) // disabled for Kotlin Compatibility Testing
  kover(projects.arrowCoreResult4k)
  kover(projects.arrowExceptionUtils)
  kover(projects.suspendapp)
  kover(projects.suspendappKtor)
}

dependencies {
  dokka(projects.arrowAnnotations)
  dokka(projects.arrowAtomic)
  dokka(projects.arrowAutoclose)
  dokka(projects.arrowCore)
  dokka(projects.arrowCoreHighArity)
  dokka(projects.arrowCache4k)
  dokka(projects.arrowEval)
  dokka(projects.arrowFunctions)
  dokka(projects.arrowPlatform)
  dokka(projects.arrowFxCoroutines)
  dokka(projects.arrowFxStm)
  dokka(projects.arrowCollectors)
  dokka(projects.arrowOptics)
  dokka(projects.arrowOpticsReflect)
  // dokka(projects.arrowOpticsCompose) // disabled for Kotlin Compatibility Testing
  dokka(projects.arrowResilience)
  // dokka(projects.arrowCoreJackson) // disabled for Kotlin Compatibility Testing
  // dokka(projects.arrowCoreJackson2) // disabled for Kotlin Compatibility Testing
  // dokka(projects.arrowCoreRetrofit) // disabled for Kotlin Compatibility Testing
  // dokka(projects.arrowCoreSerialization) // disabled for Kotlin Compatibility Testing
  dokka(projects.arrowResilienceKtorClient)
  // dokka(projects.arrowRaiseKtorServer) // disabled for Kotlin Compatibility Testing
  // dokka(projects.arrowRaiseKtorServerResources) // disabled for Kotlin Compatibility Testing
  dokka(projects.arrowCoreResult4k)
  dokka(projects.arrowExceptionUtils)
  dokka(projects.suspendapp)
  dokka(projects.suspendappKtor)
}

dokka {
  dokkaPublications.html {
    outputDirectory.set(layout.projectDirectory.dir("docs"))
  }
  moduleName.set("Arrow")
  pluginsConfiguration.html {
    customAssets.from("static/img/logo/logo-icon.svg")
    footerMessage.set("© Arrow Contributors")
  }
}
