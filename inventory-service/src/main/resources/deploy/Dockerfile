# Define variables
ARG APP_NAME=inventory-service
ARG APP_VERSION=0.0.1

# Use openEuler 24.03 as the basic image.
FROM openeuler/openeuler:24.03

# Set environment variables to avoid interactive configuration prompts.
ENV DEBIAN_FRONTEND=noninteractive

# Update the packages and install JDK 17
RUN dnf update -y && \
    dnf install java-17-openjdk-devel -y && \
    dnf clean all

#Copying microservice files
RUN mkdir -p /opt/inventory-service
COPY ./*.jar /opt/inventory-service/
RUN chmod a+x /opt/inventory-service -R

#Set Logs
VOLUME /opt/logs/inventory-service

# Exposure of application ports (based on requirements)
EXPOSE 9090

# Startup Commands
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-XX:InitialRAMPercentage=50", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=200", "-XX:+ExitOnOutOfMemoryError", "-Xlog:gc*", "-jar", "/opt/inventory-service/inventory-service-0.0.1-SNAPSHOT.jar"]

