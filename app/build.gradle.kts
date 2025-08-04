plugins {
    application // 👈 Needed if you use `main()` entry point
    java
    id("checkstyle")
    id("org.sonarqube") version("4.4.1.3373")
    jacoco
}

// ✅ Added group and version to fix Hexlet CI
group = "hexlet.code"
version = "1.0.0"

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

// ✅ Application main class (used if you run the app)
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

// ✅ Java 21
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// ✅ Enable JUnit 5
tasks.test {
    useJUnitPlatform()
    dependsOn(tasks.clean) // 👈 Clean before testing
    finalizedBy(tasks.jacocoTestReport) // Run coverage report after tests
}

// ✅ Fail on warnings (good for quality)
tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}

// ✅ Run Checkstyle automatically with `check`
tasks.check {
    dependsOn("checkstyleMain", "checkstyleTest")
}

// ✅ JaCoCo (test coverage reporting)
jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)  // 👈 Required for SonarCloud
        html.required.set(false)
        csv.required.set(false)
    }
}

// ✅ Checkstyle config
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
