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

    // test ----------------------------------------------------------------------------------------
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("junit:junit:4.12")
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