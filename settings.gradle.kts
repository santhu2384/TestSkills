pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestSkills"
include(":app")
include(":domain")
include(":data")


include(":core:common")
include(":core:network")
include(":core:di")
include(":core:designsystem")
include("feature:live")
include("feature:home")
include("feature:player")
include(":core:ui")
