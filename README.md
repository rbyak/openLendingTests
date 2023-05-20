# openLendingTests
Tests to verify no duplication of blog entries 

##Project Structure 

| Structure  | Dependency         |
|------------|--------------------|
| Base       | Selenium Webdriver |
| Logging    | Logback            |
| Assertions | Assertj            |
| Test       | junit              |
| Bßuild     | Maven              |
- Test is present under openLendingTests/src/test/java/com
- Test starts at google homepage since it was mentioned in the problem statement and navigate to the relative pages
- Pre-req: installed: java, selenium webdriver, docker

#Setup To run tests :

- once cloned on your local computer using terminal cd into openLendingTests via terminal
- enter "docker-compose pull && docker-compose up"
- once the container is up
- enter "mvn clean test" in another terminal window (also cd into repo first)
- Please use junit for run configuration if you need to run locally
- Ide to create this project: Intellij
