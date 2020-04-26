pluginManagement {
  buildscript {
    repositories {
      maven(uri("https://repo.spring.io/milestone"))
      gradlePluginPortal()
      mavenCentral()
    }
  }
  val kotlinVersion = "1.3.72"
  extra["kotlinVersion"] = kotlinVersion
  plugins {
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.ajoberstar.reckon") version "0.12.0"
    id("com.palantir.docker-run") version "0.25.0"
    id("org.springframework.boot") version "2.3.0.M4"
    id("com.github.ben-manes.versions") version "0.28.0"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
  }
  repositories {
    maven(uri("https://repo.spring.io/milestone"))
    gradlePluginPortal()
    mavenCentral()
  }
}
rootProject.name = "movie-catalog-system"
include(
  "movie-details",
  "movie-ratings",
  "movie-catalog"
)
