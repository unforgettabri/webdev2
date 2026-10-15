# Task Tracker

A simple Task Tracker web application built with Java, Spring Boot, and Thymeleaf.

## Features
- View all tasks
- View individual task details
- Create tasks using a form
- Validate task titles and descriptions
- Automatically assign task IDs
- Display a friendly error page for tasks that do not exist
- Store tasks in memory

## Technologies
- Java 17
- Spring Boot
- Spring Web
- Thymeleaf
- Jakarta Bean Validation
- Maven

## Run the Application
```bash
./mvnw spring-boot:run
```

Open `http://localhost:8080/tasks`.

## Run Tests
```bash
./mvnw clean test
```

## Storage
Tasks are stored in memory and will reset when the application restarts.