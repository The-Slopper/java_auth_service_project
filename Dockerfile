# Java Auth Service - (c) 2026 Example Org
FROM eclipse-temurin:11-jdk

WORKDIR /app

COPY . .
RUN mvn clean install

EXPOSE 3000

CMD ["sh", "-c", "mvn exec:java"]
