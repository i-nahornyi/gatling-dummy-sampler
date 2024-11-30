package dummy

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.github.i.nahornyi.dummy.sampler.DummyDsl.dummySampler
import io.github.i.nahornyi.dummy.sampler.DummyDsl.KO

class DummySimulationsScala extends Simulation {


  def mainScenario: ScenarioBuilder = scenario("mocked_scenario").repeat(5) {
    group("dummyTransaction") {
      exec(
        dummySampler("dummyRequestA", "#{randomLong(250,1000)}"),
        pause(1, 5),
        exec(session => session.set("respTime", 200.toLong)),
        dummySampler(session => "dummyRequestB", session => session("respTime").as[Long]),
        pause(1, 5),
        exec(session => session.set("requestName", "dummyRequestC")),
        dummySampler("#{requestName}", session => 500.toLong),
        pause(1, 5),
        dummySampler(session => session("requestName").as[String].replace("C", "D"), "#{randomLong(250,1000)}"),
        pause(1, 5),
        randomSwitch(
          90.0 -> dummySampler("dummyRequestE", "#{randomLong(250,1000)}"),
          10.0 -> dummySampler("dummyRequestE", "#{randomLong(250,1000)}", KO)
        )
      )
    }
  }

  setUp(mainScenario.inject(rampUsers(10).during(30)))

}
