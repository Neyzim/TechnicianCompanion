FROM eclipse-temurin:21
LABEL mantainer="https://github.com/Neyzim"
WORKDIR /TechnicianCompanion
COPY target/TechnicianCompanion-0.0.1-SNAPSHOT.jar /TechnicianCompanion/TechnicianCompanion-app
ENTRYPOINT ["java", "-jar", "TechnicianCompanion-app"]
