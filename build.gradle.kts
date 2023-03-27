

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

tasks.withType<JavaExec> {
    systemProperty("ebean.ddl.generate", "true")
}

plugins {
    kotlin("kapt") version "1.8.10"
    id("io.ebean") version "13.15.0"
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.4"
    kotlin("plugin.serialization") version "1.8.10"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-resources:$ktor_version")
    implementation("io.ktor:ktor-server-openapi:$ktor_version")
    implementation("io.swagger.codegen.v3:swagger-codegen-generators:1.0.38")
    implementation("io.ebean:ebean:13.15.0")
    kapt("io.ebean:querybean-generator:13.15.0")
    testImplementation("io.ebean:ebean-test:13.15.0")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("io.ebean:ebean-migration:13.7.0")
    implementation("io.ebean:ebean-ddl-generator:13.15.0")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")
}

tasks.test{
    useJUnitPlatform()
}


