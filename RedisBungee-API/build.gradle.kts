plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}


val jedisVersion = "4.3.2"
val configurateVersion = "3.7.2"
val guavaVersion = "31.1-jre"


dependencies {
    api("com.google.guava:guava:$guavaVersion")
    api("redis.clients:jedis:$jedisVersion")
    api("com.squareup.okhttp:okhttp:2.7.5")
    api("org.spongepowered:configurate-yaml:$configurateVersion")

    // tests
    testImplementation("junit:junit:4.13.2")
}

description = "RedisBungee interafaces"

java {
    withJavadocJar()
    withSourcesJar()
}

tasks {
    // thanks again for paper too
    withType<Javadoc> {
        val options = options as StandardJavadocDocletOptions
        options.use()
        options.isDocFilesSubDirs = true
        options.links(
            "https://configurate.aoeu.xyz/$configurateVersion/apidocs/", // configurate
            "https://javadoc.io/doc/redis.clients/jedis/$jedisVersion/", // jedis
            "https://guava.dev/releases/$guavaVersion/api/docs/" // guava
        )
    }

    test {
        useJUnitPlatform()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(8)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()

    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

