module.exports = {
  apps : [
    {
      name   : "student-service",
      script : "java -jar edusubmit-student-service/target/edusubmit-student-service-0.0.1-SNAPSHOT.jar",
      log_file: "./logs/edusubmit-student-service.log",
      instances: 2
    },
    {
      name   : "submission-service",
      script : "java -jar edusubmit-submission-service/target/edusubmit-submission-service-0.0.1-SNAPSHOT.jar",
      log_file: "./logs/edusubmit-submission-service.log",
      instances: 2
    }
  ]
}
