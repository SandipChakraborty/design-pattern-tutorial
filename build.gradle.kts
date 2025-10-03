plugins {
    id("org.springframework.boot") version "3.3.3" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
    group = "com.design.pattern"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    // Apply plugins
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    // Configure Java 21 toolchain
    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    // Compile to Java 21 bytecode
    tasks.withType<JavaCompile>().configureEach {
        options.release.set(21)
    }

    // ✅ Dependencies block
    project.dependencies {
        add("implementation", "org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
        add("testImplementation", "org.junit.jupiter:junit-jupiter:5.10.3")
        add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")
    }

    // ✅ Test task configuration
    project.tasks.named("test", Test::class) {
        useJUnitPlatform()
    }
}
