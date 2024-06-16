import org.gradle.configurationcache.extensions.fileSystemEntryType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	application
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
}

group = "com.github.faening"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

sourceSets {
	main {
		resources {
			srcDir("src/main/resources")
		}
	}
	test {
		resources {
			srcDir("src/test/resources")
		}
	}
}

dependencies {
	// Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
	runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.1")

	// MySQL
	runtimeOnly("com.mysql:mysql-connector-j:8.0.33")

	// Flyway
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")

	// Retrofit2
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

	// Reactor
	implementation("io.projectreactor:reactor-core:3.6.6")
	implementation("org.reactivestreams:reactive-streams:1.0.4")

	// DotENV
	// https://github.com/cdimascio/dotenv-kotlin
	implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

	// JUnit 5
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Mockito
	testImplementation("org.mockito:mockito-core:5.11.0")

	// H2 For Testing
	testImplementation("com.h2database:h2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Copy>().configureEach {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}