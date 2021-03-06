[![codecov](https://codecov.io/gh/cocktail-api/cocktails-server/branch/master/graph/badge.svg)](https://codecov.io/gh/cocktail-api/cocktails-server)
# Cocktails app
An app for storing cocktail recipes and ingredients

## Building
```mvn clean package``` will result in a runnable jar.

## Running locally
1. Spin up a local postgres database (a docker compose file is in ```docker/dev-env/compose.yml```).
Before the compose file can be started, copy the ```.env.example``` file to ```.env``` and fill in the client ID value.
2. Copy ```application-dev.yml.example``` to ```application-dev.yml``` and adjust values to match postgres configuration
3. Run application with ```./mvnw spring-boot:run -Dspring-boot.run.profiles=dev```

## Running tests
By default, the tests require a working Docker installation for use with Testcontainers.
This is used for creating a clean postgres database for every test class. Tests can also be
run without docker in a shared database. For this, copy ```no-docker-tests.properties.example```
to ```no-docker-tests.properties``` and adjust the datasource values so that they point to a valid
postgres database. The tests can then be run with ```mvn -P no-docker test```.
