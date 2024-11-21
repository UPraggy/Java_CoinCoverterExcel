/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.10.2/userguide/building_java_projects.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("org.apache.poi:poi-ooxml:4.1.2")
    implementation("org.apache.poi:poi-ooxml-schemas:4.1.2")
    implementation("org.apache.xmlbeans:xmlbeans:3.1.0") // Versão compatível com 4.1.2
    
    
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.11.0")


}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit4 test framework
            useJUnit("4.13.2")
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    // Define the main class for the application.
    mainClass = "coin_coverter_excel.Main"
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}


// Configura o manifest para o .jar
tasks.jar {
    manifest {
        attributes["Main-Class"] = "coin_coverter_excel.Main" // Substitua pelo caminho correto da classe principal
    }

    
}


tasks.register("fatJar", Jar::class) {
    group = "build"
    description = "Assembles a fat JAR including all dependencies."

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveClassifier.set("fat")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    manifest {
        attributes["Main-Class"] = "coin_coverter_excel.Main"
    }
}

//java -jar app/build/libs/app.jar

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}