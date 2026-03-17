FROM maven:3.9.4-eclipse-temurin-17

WORKDIR /workspace
COPY . /workspace

# Use maven to build without tests (faster). You can run 'mvn package' inside container without -DskipTests.
RUN mvn -DskipTests package

CMD ["java","-jar","target/virtual-engine-0.0.1-SNAPSHOT.jar"]
