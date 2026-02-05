pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "AndroidLab2"
include(":app")
include(":ch2")
include(":quiz")
include(":ch3")
include(":ch4")
include(":ch5")
include(":ch6")
include(":ch7")
include(":ch7_outer")
include(":ch8")
include(":ch9")
include(":ch10")
