plugins {
    application
    java
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("hexlet.code.App")
}

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
    dependsOn(tasks.clean)
    finalizedBy(tasks.jacocoTestReport)
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}

tasks.check {
    dependsOn("checkstyleMain", "checkstyleTest")
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(false)
        csv.required.set(false)
    }
}

checkstyle {
    toolVersion = "10.12.1"
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
}

// 🚫 SonarCloud is commented out to avoid CI failure
// sonar {
//     properties {
//         property("sonar.projectKey", "YourProjectKey")
//         property("sonar.organization", "YourOrg")
//         property("sonar.host.url", "https://sonarcloud.io")
//         property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml")
//     }
// }
