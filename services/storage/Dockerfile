# Этап 1 - сборка проекта в jar
FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /train_storage
COPY . .
RUN mvn install -Dmaven.test.skip=true

# Этап 2 - запуск поекта
FROM openjdk:17.0.2-jdk
WORKDIR /train_storage
COPY --from=build /train_storage/target/storage-0.1.jar storage.jar
CMD ["java", "-jar", "storage.jar"]