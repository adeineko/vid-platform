plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "be.kdg"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.webjars:bootstrap:5.3.2")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation ("org.springframework.boot:spring-boot-starter-jdbc")
	runtimeOnly ("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	implementation("org.postgresql:postgresql:42.6.0")
	implementation("org.webjars:bootstrap:5.3.2")
	implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

