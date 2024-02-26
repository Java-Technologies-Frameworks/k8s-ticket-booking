FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY build/libs/* build/lib/

COPY build/libs/k8s-ticket-booking-application-1.0.0.jar build/

WORKDIR /app/build
EXPOSE 8080
ENTRYPOINT java -jar k8s-ticket-booking-application-1.0.0.jar