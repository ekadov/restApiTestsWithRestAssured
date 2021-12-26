#README

##CRUD tests for Restful Booker Application.

###Used:

   * Maven or Gradle (use what you want)
   * Rest Assured
   * JUnit 5
   * AssertJ
   * Faker
   * Log4J2

These are CRUD tests for API provided. Made positive and negative tests in separate classes.

###@TODO

   * Create a smoke tests pack by adding @Smoke annotation to them
   * Add more negative tests
   * Add more custom WebElements
   * Migrate SQL scripts into specific config file and create a class to use it


###Running tests
1. Download testable application here: https://github.com/mwinteringham/restful-booker
   
2. Installation
* Ensure mongo is up and running by executing 'mongod' in your terminal
* Clone the repo
* Navigate into the 'restful-booker' root folder
* Run 'npm install'
* Run 'npm start'

Or you can run this via Docker:
* Clone the repo
* Navigate into the 'restful-booker' root folder
* Run 'docker-compose build'
* Run 'docker-compose up'
* APIs are exposed on http://localhost:3001


3. Run 'mvn clean test' or './gradlew clean test'