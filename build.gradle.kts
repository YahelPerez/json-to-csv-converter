// build.gradle.kts
plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    //Dependencies for the main code
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.apache.commons:commons-csv:1.10.0")

    // Dependencies ONLY for testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("com.naodigital.program.Main")
}

//Block for configuring how the tests are executed
tasks.withType<Test>{
    useJUnitPlatform()
}