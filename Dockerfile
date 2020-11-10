# Creates a container to compile the java file with Maven
FROM openjdk:15-alpine AS builder
RUN apk add --no-cache curl tar bash procps
ARG MAVEN_VERSION=3.6.3
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && echo "Downlaoding maven" \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  \
  && echo "Unziping maven" \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  \
  && echo "Cleaning and setting links" \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
COPY src /usr/src/a4/src
COPY pom.xml /usr/src/a4/
WORKDIR /usr/src/a4
RUN mvn clean install -DskipTests

#Second container to run the file. The file that is created above is copied into this container.
FROM openjdk:15-alpine
RUN addgroup -S A82 && adduser -S spring -G A82
USER spring:A82
ARG JAR_FILE=target/*.jar
COPY --from=builder /usr/src/a4/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]