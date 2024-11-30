# DummySampler Java

## Before
_add import_
```Java
import static io.github.i.nahornyi.dummy.sampler.javaapi.DummyDsl.dummySampler;
```

## Sampler Name _**(String)**_

```scala
// with a static value
dummySampler("dummyRequestA", "#{randomLong(250,1000)}")
// with a dynamic value computed from a Gatling Expression Language String
dummySampler("#{requestName}", "#{randomLong(250,1000)}")
// a dynamic value computed from a function
dummySampler(session -> session.getString("requestName"),"#{randomLong(250,1000)}")
```
## Duration _**(Long)**_

```scala
// with a dynamic value computed from a Gatling Expression Language String
dummySampler("requestA", "#{randomLong(250,1000)}")
// a dynamic value computed from a function
dummySampler("#{requestName}", session -> session.getLong("responseTime"))
dummySampler("#{requestName}", session -> ThreadLocalRandom.current().nextLong(100,500)) /// Not recommended
```

## Status _**(default DummyStatus.OK)**_
```scala
// Set status KO
dummySampler("requestA", "#{randomLong(250,1000)}", DummyStatus.KO)

/// Emulate Flaky request
randomSwitch().on(
  percent(90).then(dummySampler("requestA","#{randomLong(250,1000)}")),
  percent(10).then(dummySampler("requestA","#{randomLong(250,1000)}", KO))
)
```