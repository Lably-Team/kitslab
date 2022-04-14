plugins {
    id("java")
}

group = "com.github.lablyteam"

repositories {
    mavenCentral()
    maven("https://repo.codemc.org/repository/nms/")
}

dependencies {
    implementation(project(":api"))

    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
}