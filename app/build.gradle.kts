plugins {
    java
    id("org.sonarqube") version "4.4.1.3373" // ← Add this line
}

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 (Jupiter)
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    // AssertJ
    testImplementation("org.assertj:assertj-core:3.24.2")

    // JUnit platform launcher (optional but useful)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

// SonarCloud configuration ↓↓↓ Replace these values with your actual ones
sonar {
    properties {
        property("sonar.projectKey", "VasylP0_java-project-78")
        property("sonar.organization", "vasylp0")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
