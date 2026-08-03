# API Test Framework

![API Tests](https://github.com/nabilah-ss/api-test-framework/actions/workflows/test.yml/badge.svg)

REST Assured + TestNG API automation framework. Built as a portfolio project.

## Stack
- Java 17, Maven
- REST Assured 5.5, TestNG 7.10
- Docker, docker-compose
- WireMock (service virtualization)
- GitHub Actions CI

## Target API
[restful-booker](https://restful-booker.herokuapp.com) + WireMock stubs

## Run Locally
```bash
mvn test
```

## Run in Docker
```bash
docker compose build
docker compose run --rm api-tests
```

## CI Pipeline
Every push triggers GitHub Actions → Docker build → tests against WireMock.