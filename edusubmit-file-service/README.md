# edusubmit-file-service

Spring Boot 4.x microservice for assignment file uploads and downloads using Google Cloud Storage.

## Tech Stack

- Java 25
- Maven
- Spring Web
- Validation
- Eureka Client
- Config Client
- Spring Boot Actuator
- Google Cloud Storage SDK
- Multipart file upload

## Package

`com.edusubmit.fileservice`

## Features

- Upload assignment files to a GCP bucket
- Retrieve file metadata by `fileId`
- Download files by `fileId`
- In-memory metadata map for local development
- Registers with Eureka and loads config from Config Server

## Endpoints

- `POST /api/files/upload`
- `GET /api/files/{fileId}`
- `GET /api/files/download/{fileId}`

## Metadata Fields

- `fileId`
- `fileName`
- `contentType`
- `bucketPath`
- `uploadedBy`
- `uploadedAt`

## Configuration

`src/main/resources/application.yml` includes placeholders:

- `gcp.storage.bucket-name` -> `${GCP_BUCKET_NAME:your-bucket-name}`
- `gcp.storage.credentials-path` -> `${GCP_CREDENTIALS_PATH:}`
- `gcp.storage.project-id` -> `${GCP_PROJECT_ID:}`

Also configured:

- Service port: `8083`
- Config import: `optional:configserver:http://localhost:8888`
- Eureka default zone: `http://localhost:8761/eureka/`
- Multipart upload max size: `25MB`
- Health endpoint: `http://localhost:8083/actuator/health`

## Setup (Local)

1. Ensure Java 25 and Maven are installed.
2. Create or select a GCS bucket.
3. Create a service account with storage permissions and download its JSON key.
4. Export environment variables:

```bash
export GCP_BUCKET_NAME=your-bucket-name
export GCP_CREDENTIALS_PATH=/absolute/path/to/service-account.json
export GCP_PROJECT_ID=your-gcp-project-id
```

5. Start dependencies first:
- Config Server (`http://localhost:8888`)
- Eureka Server (`http://localhost:8761`)

6. Run service:

```bash
cd edusubmit-file-service
mvn spring-boot:run
```

## Example cURL Requests

### Upload File

```bash
curl -X POST http://localhost:8083/api/files/upload \
  -F "file=@/absolute/path/to/assignment1.pdf" \
  -F "uploadedBy=student-2001"
```

### Get File Metadata

```bash
curl http://localhost:8083/api/files/<fileId>
```

### Download File

```bash
curl -L http://localhost:8083/api/files/download/<fileId> -o downloaded-assignment.pdf
```

## Notes

- Metadata is stored in-memory for local development only; restarting the service clears metadata entries.
- If metadata exists but object is missing in GCS, download endpoint returns `404`.
# edusubmit-file-service
