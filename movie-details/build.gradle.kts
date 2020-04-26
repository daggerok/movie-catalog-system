plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.springframework.boot")
  // id("com.palantir.docker-run")
  id("io.spring.dependency-management")
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test")// { exclude(group = "org.junit.vintage", module = "junit-vintage-engine") }
  testImplementation("io.projectreactor:reactor-test")
}

springBoot {
  buildInfo()
}

tasks {
  bootJar { // withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    launchScript()
  }
}
