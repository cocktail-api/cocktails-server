# Cocktails app
An app for storing cocktail recipes and ingredients

## Building
```mvn clean package``` will result in a runnable jar.

## Running tests
By default, the tests require a working Docker installation for use with Testcontainers.
This is used for creating a clean postgres database for every test class. Tests can also be
run without docker in a shared database. For this, copy ```no-docker-tests.properties.example```
to ```no-docker-tests.properties``` and adjust the datasource values so that they point to a valid
postgres database. The tests can then be run with ```mvn -P no-docker test```.
