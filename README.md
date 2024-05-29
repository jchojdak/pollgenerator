# PollGenerator
### Information:
PollGenerator is an application for creating and voting in anonymous polls.

### Types:
* SINGLE_CHOICE
* MULTIPLE_CHOICE

### Before starting:
Create a `.env` file in the project's directory and add the following environment variables:
```
MYSQLDB_USER=user
MYSQLDB_PASSWORD=password
MYSQLDB_ROOT_PASSWORD=P@ssw0rd
MYSQLDB_DATABASE=pollgenerator
```

### Starting up
To launch the application, follow the steps:
1. Clone project
```
git clone https://github.com/jchojdak/pollgenerator.git
```
2. Open cloned directory
```
cd pollgenerator
```
3. Start the application using docker-compose
```
docker-compose up -d
```

### Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```