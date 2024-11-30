# Gatling Dummy Sampler

The **Gatling Dummy Sampler** is a plugin for simulating realistic actions without making actual requests. It is especially useful when:

- The request involves a cost.
- The response times are already known or predictable.

## Use Case

Imagine a scenario where you are testing an integration with a third-party API. This API charges for each request, but you already know its typical response times. Instead of incurring extra costs or relying on the API during testing, you can use the Dummy Sampler to:

1. **Simulate Response Times**: Emulate the known response time range of the API.
2. **Mock Success and Failure**: Generate responses to represent both successful and failed requests, mimicking real-world conditions.

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
