
FROM eclipse-temurin:17

LABEL maintainer="sumitsdhandar@gmail.com"

WORKDIR /user/app

COPY target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar /user/app/springboot-restful-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-restful-webservices.jar"]


