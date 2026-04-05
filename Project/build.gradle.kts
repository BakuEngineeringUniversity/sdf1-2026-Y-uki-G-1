plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "com.cookingapp"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("cooking.MainKt")
}

kotlin {
    jvmToolchain(17)
}
