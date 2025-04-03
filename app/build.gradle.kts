plugins {
    application
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

application {
    // Можно пока закомментировать или указать реальный main-класс позже
    // mainClass = "hexlet.code.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
