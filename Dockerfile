FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package -U -DskipTests -Dmaven.javadoc.skip=true

FROM amazoncorretto:21-alpine3.19
WORKDIR /usr/app
COPY --from=build /usr/src/app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
