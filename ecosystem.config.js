module.exports = {
  apps : [
    {
      name   : "edusubmit-file-service",
      script : "java -jar edusubmit-file-service/target/edusubmit-file-service-0.0.1-SNAPSHOT.jar",
      log_file: "./logs/edusubmit-file-service.log",
      instances: 2
    },
    {
      name   : "edusubmit-student-service",
      script : "java -jar edusubmit-student-service/target/edusubmit-student-service-0.0.1-SNAPSHOT.jar",
      log_file: "./logs/edusubmit-student-service.log",
      instances: 2
    },
    {
      name   : "edusubmit-submission-service",
      script : "java -jar edusubmit-submission-service/target/edusubmit-submission-service-0.0.1-SNAPSHOT.jar",
      log_file: "./logs/edusubmit-submission-service.log",
      instances: 2
    }
  ]
}
