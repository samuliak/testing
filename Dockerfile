FROM openjdk:16
ADD target/spring-boot-testing.jar spring-boot-testing.jar
ENTRYPOINT ["java","-jar","/spring-boot-testing.jar"]