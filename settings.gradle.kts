@file:Suppress("UnstableApiUsage")

include(":features:recommendations_feature")


include(":features:home_feature")


include(":features:authorization_feature")


include(":data:frameworks")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Cooklyn"
include(":app")
include(":common")
include(":features")
include(":features:welcome_feature")
include(":navigation")
include(":domain")
include(":data")
include(":data:network")
include(":data:database")
include(":data:repository")
