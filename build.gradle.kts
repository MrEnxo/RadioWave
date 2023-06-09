import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.mrenxo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "citizens-repo"
        url = uri("https://maven.citizensnpcs.co/repo")
    }
    maven {
        name = "codemc-repo"
        url = uri("https://repo.codemc.io/repository/maven-public/")
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("net.citizensnpcs:citizens-main:2.0.30-SNAPSHOT") {
        exclude(group = "*", module = "*")
    }
    implementation("de.tr7zw:item-nbt-api-plugin:2.11.2")
    implementation("fr.mrmicky:fastboard:1.2.1")
    implementation("org.reflections:reflections:0.10.2")
}



tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.getByName<ShadowJar>("shadowJar") {
    relocate("fr.mrmicky.fastboard", "me.mrenxo.utils.fastboard")
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}