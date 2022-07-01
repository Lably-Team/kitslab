plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.github.lablyteam"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    implementation(project(":api"))
    implementation("com.google.inject:guice:4.0")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-chat:1.8-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:23.0.0")
}

tasks {
    processResources {
        expand(
            "version" to version
        )
    }

    shadowJar {
        archiveBaseName.set("KitsLab")
        destinationDirectory.set(file("$rootDir/bin/"))
        minimize()

        relocate("com.google.inject.guice", "com.github.lablyteam.kitslab.libs.guice")
        relocate("org.jetbrains.annotations", "com.github.lablyteam.kitslab.libs.annotations")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    clean {
        delete("$rootDir/bin/")
    }
}