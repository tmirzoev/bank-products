# Description
This a test project showcasing simple architecture and unit tests.

The task was to write classes for several banking products, and provide it with unit tests.
The architecture had to be flexible, and easy to extend with additional banking products and functionality.

The products are: 
 * debit card
 * credit card
 * savings deposit
All the products support multiple currencies.
 
Due to how this test task focuses on OOP, method functionality was implemented in a basic way.

# How to run
This a simple java 11/gradle project with gradle provided by gradle wrapper, so you can just build it and run tests with `./gradlew clean build test`.

If building/running with IntelliJ IDEA, don't forget to enable Lombok plugin and ensure that Lombok is enabled as annotation processor.

