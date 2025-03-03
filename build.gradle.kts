plugins {
	java
	id("org.springframework.boot") version "2.7.14"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.bookmyshowspring"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(11)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.modelmapper:modelmapper:2.1.1")
	compileOnly("org.projectlombok:lombok:1.18.36")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
