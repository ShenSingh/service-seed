# edusubmit-submission-service

Spring Boot 4.x Submission Service for EduSubmit.

## Tech Stack

- Java 25
- Maven
- Spring Web
- Spring Data MongoDB
- Validation
- Eureka Client
- Config Client
- Actuator

## Package

`com.edusubmit.submissionservice`

## Mongo Documents

### Assignment

- id
- courseId
- title
- description
- dueDate
- createdBy
- createdAt

### Submission

- id
- assignmentId
- studentId
- courseId
- fileId
- fileName
- submittedAt
- status
- grade
- feedback

## Endpoints

- `POST /api/assignments`
- `GET /api/assignments`
- `GET /api/assignments/course/{courseId}`
- `POST /api/submissions`
- `GET /api/submissions/student/{studentId}`
- `GET /api/submissions/assignment/{assignmentId}`
- `PUT /api/submissions/{id}/grade`

## Configuration

- Port: `8082`
- MongoDB URI: `mongodb://localhost:27017/edusubmit_submission_db`
- Config Server: `http://localhost:8888`
- Eureka Server: `http://localhost:8761/eureka/`
- Health endpoint: `http://localhost:8082/actuator/health`

## Run

1. Start Config Server and Eureka Server.
2. Start MongoDB.
3. Run:

```bash
cd edusubmit-submission-service
mvn spring-boot:run
```

## Build

```bash
cd edusubmit-submission-service
mvn clean package
```

## Sample Requests

### Create Assignment

```bash
curl -X POST http://localhost:8082/api/assignments \
  -H "Content-Type: application/json" \
  -d '{
    "courseId": "CS101",
    "title": "Assignment 1",
    "description": "Implement data structures",
    "dueDate": "2026-12-20T23:59:00",
    "createdBy": "lecturer-1001"
  }'
```

### Get All Assignments

```bash
curl http://localhost:8082/api/assignments
```

### Get Assignments by Course

```bash
curl http://localhost:8082/api/assignments/course/CS101
```

### Create Submission

```bash
curl -X POST http://localhost:8082/api/submissions \
  -H "Content-Type: application/json" \
  -d '{
    "assignmentId": "ASSIGNMENT_ID",
    "studentId": "student-2001",
    "courseId": "CS101",
    "fileId": "file-abc123",
    "fileName": "assignment1.pdf"
  }'
```

### Get Submissions by Student

```bash
curl http://localhost:8082/api/submissions/student/student-2001
```

### Get Submissions by Assignment

```bash
curl http://localhost:8082/api/submissions/assignment/ASSIGNMENT_ID
```

### Grade Submission

```bash
curl -X PUT http://localhost:8082/api/submissions/SUBMISSION_ID/grade \
  -H "Content-Type: application/json" \
  -d '{
    "grade": 88.5,
    "feedback": "Good implementation with minor issues"
  }'
```
# edusubmit-submission-service
