# Twitter-like

This is a twitter-like application with OAuth2 and JWT.

## Pre-setup:

- Create MySQL database: twitter-like

## Environment variables

Update the following environment variables accordingly:

- MYSQL_DB_CONNECTION_URL
- MYSQL_DB_USER
- MYSQL_DB_PASS

## How to start the application locally:

- mvn spring-boot:run

## How to start the application on Docker:

### Create docker image:

- docker build -t twitter-like .

### Run docker image:

#### Linux and Windows:

*replace variables values accordingly.

- docker run -e "MYSQL_DB_CONNECTION_URL=jdbc:mysql://192.168.0.177:3306/twitter-like" -e "MYSQL_DB_USER=mysqldatabase" -e "MYSQL_DB_PASS:mysqldatabase" -p 8080:8080 twitter-like

## How to access Swagger and test the application:

1. Access Swagger page:
   - http://localhost:8080/swagger-ui/index.html#/
2. Authenticate with GitHub
3. Get JWT token from /authentication/token endpoint in Swagger
4. Use acquired token to authorize subsequent requests



