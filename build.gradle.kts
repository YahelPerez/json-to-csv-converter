// build.gradle.kts
plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.apache.commons:commons-csv:1.10.0")
}

application {
    mainClass.set("org.example.Main")
}