# DummySampler Scala

## Before
_add import_
```Scala
import io.github.i.nahornyi.dummy.sampler.DummyDsl.dummySampler
```
## Sampler Name _**(String)**_
```scala
// with a static value
dummySampler("requestA", "#{randomLong(250,1000)}")
// with a dynamic value computed from a Gatling Expression Language String
dummySampler("#{requestName}", "#{randomLong(250,1000)}");
// a dynamic value computed from a function
dummySampler(session => session("requestName").as[String], "#{randomLong(250,1000)}");
```
## Duration _**(Long)**_
```scala
// with a static value
dummySampler("requestA",500.toLong)
// with a dynamic value computed from a Gatling Expression Language String
dummySampler("requestA", "#{randomLong(250,1000)}");
// a dynamic value computed from a function
dummySampler("requestA", session => session("respTime").as[Long]);
```

## Status _**(default DummyStatus.OK)**_
```scala
// Set status KO
dummySampler("requestA", "#{randomLong(250,1000)}", DummyStatus.KO)

/// Emulate Flaky request
randomSwitch(
  90.0 -> dummySampler("requestA", "#{randomLong(250,1000)}"),
  10.0 -> dummySampler("requestA", "#{randomLong(250,1000)}", KO)
)
```