# Email Checker

## Overview
This little web service purpose is to count unique emails. It exposes a single endpoint and method `POST /v1/emails` which consumes
a list of strings in JSON format and returns a single integer that represent how many unique emails the input had.

NOTES:
- [Gmail email equivalence](https://gmail.googleblog.com/2008/03/2-hidden-ways-to-get-more-from-your.html) is respected
- Invalid emails are ignored from the final count
- No server timeout was configured to allow for large email lists. Asyncronous processing is a potential improvement

# Dependencies
- [maven](https://maven.apache.org/install.html)
- [java 8+](https://openjdk.java.net/install/)

## Usage
- `mvn clean spring-boot:run` will start the service in port 80
- To count emails, make a `POST` request with your favourite HTTP client. Ex: `curl -X POST -H 'Content-Type: application/json' -d '["test@test.com", ... ]' "http://localhost/v1/emails"`

## Test
- `mvn clean test` will run the unit tests