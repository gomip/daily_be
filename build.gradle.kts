import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
    mavenCentral()
    jcenter()
    google()
}

dependencies {
    // spring --------------------------------------------------------------------------------------
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.4.3")

    // oauth ---------------------------------------------------------------------------------------
//    implementation("org.springframework.security:spring-security-oauth2-client:5.4.5")
//    implementation("org.springframework.security:spring-security-oauth2-jose:5.4.5")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client:2.4.3")
    implementation("com.google.api-client:google-api-client:1.31.3")
    implementation("com.google.auth:google-auth-library-oauth2-http:0.25.0")
    implementation("com.google.apis:google-api-services-oauth2:v2-rev157-1.25.0")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.auth0:java-jwt:3.14.0")
    implementation("com.google.http-client:google-http-client-jackson2:1.39.1")
//    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.2")

    // aop -----------------------------------------------------------------------------------------
    implementation ("org.springframework.boot:spring-boot-starter-aop")

    // kotlin --------------------------------------------------------------------------------------
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //jasypt ---------------------------------------------------------------------------------------
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.3")

    // mybatis -------------------------------------------------------------------------------------
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")

    // jpa -----------------------------------------------------------------------------------------
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // database ------------------------------------------------------------------------------------
    implementation("org.postgresql:postgresql:42.2.18")

    // swagger -------------------------------------------------------------------------------------
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    // log -----------------------------------------------------------------------------------------
    implementation("ch.qos.logback:logback-classic")
    implementation("ch.qos.logback:logback-core")

    // apache --------------------------------------------------------------------------------------
    implementation("commons-io:commons-io:2.8.0")
    implementation("org.apache.commons:commons-lang3:3.9")

    // pagehelper ----------------------------------------------------------------------------------
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.3.0")

    // test ----------------------------------------------------------------------------------------
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("junit:junit:4.12")

    // Json ----------------------------------------------------------------------------------------
    implementation("org.json:json:20210307")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}