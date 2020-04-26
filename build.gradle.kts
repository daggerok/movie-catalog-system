plugins {
  idea
  kotlin("jvm")
  kotlin("plugin.spring")
  id("org.ajoberstar.reckon")
  id("com.github.ben-manes.versions")
  id("io.spring.dependency-management")
}

val javaVersion = JavaVersion.VERSION_11

idea {
  project {
    languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel(javaVersion)
  }
  module {
    isDownloadJavadoc = true
    isDownloadSources = true
  }
}

allprojects {
  apply<JavaPlugin>()
  apply<io.spring.gradle.dependencymanagement.DependencyManagementPlugin>()

  java.sourceCompatibility = javaVersion

  configurations {
    compileOnly {
      extendsFrom(configurations.annotationProcessor.get())
    }
  }

  repositories {
    mavenCentral()
    maven(uri("https://repo.spring.io/milestone"))
  }

  dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    // implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    // implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
  }

  dependencyManagement {
    imports {
      mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
  }

  defaultTasks("clean", "build")

  tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "$javaVersion"
      }
    }
    withType<Test> {
      useJUnitPlatform()
      testLogging {
        showCauses = true
        showExceptions = true
        showStackTraces = true
        showStandardStreams = true
      }
    }
  }
}

tasks.withType(Wrapper::class.java) {
  gradleVersion = "${property("gradleVersion")}"
  distributionType = Wrapper.DistributionType.BIN
}

reckon {
  scopeFromProp()
  // stageFromProp()
  snapshotFromProp()
}

tasks {
  register("version") {
    println(project.version.toString())
  }
  register("status") {
    doLast {
      val status = grgit.status()
      status?.let { s ->
        println("workspace is clean: ${s.isClean}")
        if (!s.isClean) {
          if (s.unstaged.allChanges.isNotEmpty()) {
            println("""all unstaged changes: ${s.unstaged.allChanges.joinToString(separator = "") { i -> "\n - $i" }}""")
          }
        }
      }
    }
  }
}
