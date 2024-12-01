val reflectionVersion = "0.10.2"
val lombokVersion = "1.18.34"
val slf4jVersion = "2.0.9"

plugins {
    id("java")
    id("application")
}

group = "dev.zooty"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "dev.zooty.AdventOfCode"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.reflections:reflections:$reflectionVersion")
    implementation("org.slf4j:slf4j-nop:$slf4jVersion")
    compileOnly ("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor ("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly ("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor ("org.projectlombok:lombok:$lombokVersion")
}

tasks.test {
    useJUnitPlatform()
}