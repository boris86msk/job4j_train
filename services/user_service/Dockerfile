# Этап 1 - сборка проекта в jar
FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /train_user_service
COPY . .
RUN mvn install -Dmaven.test.skip=true

# Этап 2 - запуск поекта
FROM openjdk:17.0.2-jdk
WORKDIR /train_user_service
COPY --from=build /train_user_service/target/user_service-0.1.jar user_service.jar
CMD ["java", "-jar", "user_service.jar"]