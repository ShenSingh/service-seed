# edusubmit-student-service

Spring Boot 4.x Student Service for EduSubmit.

## Tech Stack

- Java 25
- Maven
- Spring Web
- Spring Data JPA
- MySQL Driver
- Validation
- Eureka Client
- Config Client
- Actuator

## Package

`com.edusubmit.studentservice`

## Features

- User registration/login for student and lecturer roles
- Course creation and listing
- Enrollment creation
- Fetch courses by student id
- Global exception handling with validation errors
- Actuator health endpoint

## API Endpoints

- `POST /api/students/register`
- `POST /api/students/login`
- `GET /api/students`
- `POST /api/courses`
- `GET /api/courses`
- `POST /api/enrollments`
- `GET /api/students/{studentId}/courses`

## Configuration

- Service port: `8081`
- Eureka: `http://localhost:8761/eureka/`
- Config Server: `http://localhost:8888`
- Health endpoint: `http://localhost:8081/actuator/health`

## Run

1. Start Config Server and Eureka Server.
2. Create MySQL database if needed (or rely on `createDatabaseIfNotExist=true`).
3. Update datasource credentials in `application.yml` if needed.
4. Run:

```bash
cd edusubmit-student-service
mvn spring-boot:run
```

## cURL Examples

### Register Student

```bash
curl -X POST http://localhost:8081/api/students/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@student.com",
    "password": "password123",
    "role": "STUDENT"
  }'
```

### Register Lecturer

```bash
curl -X POST http://localhost:8081/api/students/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Lecturer",
    "email": "jane@lecturer.com",
    "password": "password123",
    "role": "LECTURER"
  }'
```

### Login

```bash
curl -X POST http://localhost:8081/api/students/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@student.com",
    "password": "password123"
  }'
```

### Get Students

```bash
curl http://localhost:8081/api/students
```

### Create Course

```bash
curl -X POST http://localhost:8081/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "code": "CS101",
    "title": "Intro to CS",
    "description": "Basics of computer science",
    "lecturerId": 2
  }'
```

### Get Courses

```bash
curl http://localhost:8081/api/courses
```

### Enroll Student

```bash
curl -X POST http://localhost:8081/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 1
  }'
```

### Get Student Courses

```bash
curl http://localhost:8081/api/students/1/courses
```

## Schema Assumptions

See [SCHEMA-ASSUMPTIONS.md](SCHEMA-ASSUMPTIONS.md).

## Security Note

Passwords are stored as plain text in this starter for simplicity. Use BCrypt hashing and JWT in production.
# edusubmit-student-service
