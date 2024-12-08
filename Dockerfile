FROM amazoncorretto:17-alpine-jdk

ADD target/qride-users.jar qride-users.jar
ENTRYPOINT ["java", "-jar", "qride-users.jar"]