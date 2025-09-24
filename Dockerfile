
FROM eclipse-temurin:17

LABEL maintainer="sumitsdhandar@gmail.com"

WORKDIR /user/app

COPY target/devops-integration.jar /user/app/devops-integration.jar

ENTRYPOINT ["java", "-jar", "devops-integration.jar"]


