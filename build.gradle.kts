plugins {
    id("java")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.google.api-client/google-api-client
    implementation("com.google.api-client:google-api-client:2.0.0")

    // https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client-jetty
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.39.0")

    // https://mvnrepository.com/artifact/com.google.apis/google-api-services-calendar
    implementation("com.google.apis:google-api-services-calendar:v3-rev20220715-2.0.0")
}

tasks.test {
    useJUnitPlatform()
}