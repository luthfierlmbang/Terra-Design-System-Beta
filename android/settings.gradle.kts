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

rootProject.name = "terra-android"
include(":terra-tokens")
include(":terra-ui")
include(":catalog")
include(":oasis-designsystem-tokens")
include(":oasis-designsystem")
include(":oasis-catalog")
