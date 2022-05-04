FROM openjdk:8-jdk-slim
COPY "./target/banking-customer-service-0.1.jar" "customer-service.jar"
ENTRYPOINT ["java","-jar","customer-service.jar"]