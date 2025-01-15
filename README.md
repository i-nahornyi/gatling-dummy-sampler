# Gatling Dummy Sampler

[![Maven Central](https://img.shields.io/maven-central/v/io.github.i-nahornyi/gatling-dummy-sampler)](https://central.sonatype.com/artifact/io.github.i-nahornyi/gatling-dummy-sampler)

The **gatling-dummy-sampler** is a plugin for simulating realistic actions without making actual requests. It is especially useful when:

- The request involves a cost.
- The response times are already known or predictable.

## Use Case
Imagine a scenario where you are testing an integration with a third-party API. 
This API charges for each request, but you want to avoid unnecessary costs during testing. Using the Dummy Sampler, you can:

1. **Mock Specific Requests in Transactions:** Replace a single request within a transaction with a mocked response, while allowing other requests to interact with the API as usual.
2. **Include Third-Party Timing Without Charges:** If you're tuning your application to meet **NFRs**, you can simulate the third-party API's response time in the transaction without incurring costs.
3. **Predict Response Time for Future Components:** Use the mocked response times to estimate the behavior of yet-to-be-implemented components or systems, aiding in proactive planning and performance evaluation.

## How to Use

| Example                                                                                                     | Documentation                                     |
|-------------------------------------------------------------------------------------------------------------|---------------------------------------------------|
| [Java](src/test/java/dummy/DummySimulationsJava.java) / [Scala](src/test/scala/dummy/DummySimulationsScala.scala) | [Java](doc/JavaDoc.md) / [Scala](doc/ScalaDoc.md) |

## TODO List

Looks like we are good! :smile:

Here's the current progress and planned features for the plugin:

- [x] Java API
- [x] Support for Gatling EL in parameters.
- [x] Create test cases with examples.
- [x] Ability to set status KO/OK.
