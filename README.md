# Twitter-like

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

#### Linux:

- docker run -p 8080:8080 twitter-like

#### Windows:

*replace variables values accordingly.

- docker run -e "MYSQL_DB_CONNECTION_URL=jdbc:mysql://192.168.0.177:3306/twitter-like" -e "MYSQL_DB_USER=mysqldatabase" -e "MYSQL_DB_PASS:mysqldatabase" -p 8080:8080 twitter-like




