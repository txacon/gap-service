FROM openjdk:8
MAINTAINER antonio.dm.chacon@gmail.com
COPY target/gap-service-*.jar /opt/gap-service/gap-service.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/opt/gap-service/gap-service.jar"]
EXPOSE 8080