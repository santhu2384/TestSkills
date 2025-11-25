plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {

    // Only pure Kotlin allowed here
    implementation(kotlin("stdlib"))

    // Optional for coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("javax.inject:javax.inject:1")
}