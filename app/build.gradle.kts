plugins {
    application // Needed if you use `main()` entry point
    java
    id("checkstyle")
    id("org.sonarqube") version("4.4.1.3373")
    jacoco
}

// ✅ Group and version for Hexlet CI
group = "hexlet.code"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // ✅ JUnit 5 (Jupiter) — API + Engine for runtime
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    // ✅ AssertJ for fluent assertions
    testImplementation("org.assertj:assertj-core:3.24.2")

    // (Optional) JUnit platform launcher for IDE integration
    // testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// ✅ Application main class (if you run the app)
application {
    mainClass.set("hexlet.code.App")
}

// ✅ Ensure Gradle recognizes correct source directories
sourceSets {
    main {
        java.srcDirs("src/main/java")
    }
    test {
        java.srcDirs("src/test/java")
    }
}

// ✅ Java 21 toolchain
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// ✅ Enable JUnit 5
tasks.test {
    useJUnitPlatform()
    dependsOn(tasks.clean) // Clean before testing
    finalizedBy(tasks.jacocoTestReport) // Run coverage report after tests
}

// ✅ Fail on warnings
tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}

// ✅ Run Checkstyle automatically with `check`
tasks.check {
    dependsOn("checkstyleMain", "checkstyleTest")
}

// ✅ JaCoCo (test coverage)
jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)  // Required for SonarCloud
        html.required.set(false)
        csv.required.set(false)
    }
}

// ✅ Checkstyle configuration
checkstyle {
    toolVersion = "10.12.1"
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
}

// ✅ SonarCloud configuration
sonar {
    properties {
        property("sonar.projectKey", "VasylP0_java-project-78")
        property("sonar.organization", "vasylp0")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml")
    }
}
