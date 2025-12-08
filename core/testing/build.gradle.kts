@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
}


dependencies {
    // Unit test
    implementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.12")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
}