plugins {
    java
    id("checkstyle") // ✅ Added Checkstyle plugin
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

// ✅ Ensure Gradle recognizes correct source directories
sourceSets {
    main {
        java.srcDirs("src/main/java")
    }
    test {
        java.srcDirs("src/test/java")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // Run coverage report after tests
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)  // 👈 SonarCloud needs XML
        html.required.set(false)
        csv.required.set(false)
    }
}

checkstyle {
    toolVersion = "10.12.1"
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
}

sonar {
    properties {
        property("sonar.projectKey", "VasylP0_java-project-78")
        property("sonar.organization", "vasylp0")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml")
    }
}
