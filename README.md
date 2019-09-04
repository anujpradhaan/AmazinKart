# AmazinKart : A Spring boot implementation

## Steps to build the running jar
1. Git Clone the repo
2. run : mvn clean package
3. The above line will run all the integration as well as unit test cases and will generate a jar.

# Following Command to run the jar
To Run with just default discounts/common discounts
```
java -jar target/app-0.0.1-SNAPSHOT.jar 
```
To Run with promotion set A 
```
java -jar target/app-0.0.1-SNAPSHOT.jar --promotionSetA
```

To Run with promotion set B
```
java -jar target/app-0.0.1-SNAPSHOT.jar --promotionSetB
```

#Test case details:
1. Both integration and Unit test cases has been added.
2. Current Coverage would be > 90%. 
