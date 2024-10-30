package dummy

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.custom.dummy.sampler.Predef.dummySampler

class DummySimulationsScala extends Simulation {


  def mainScenario: ScenarioBuilder = scenario("mocked_scenario").repeat(5) {
    group("flow-a") {
      exec(
        exec(session => session.set("requestName","requestC").set("respTime",200.toLong)),
        dummySampler("requestA", "#{randomLong(250,1000)}"),
        pause(1, 5),
        dummySampler(session => "requestB", session => session("respTime").as[Long]),
        pause(1, 5),
        dummySampler("#{requestName}", session => 500.toLong),
        pause(1, 5),
        dummySampler(session => "requestD", "#{randomLong(250,1000)}"),
        pause(1, 5)
      )
    }
  }

  setUp(mainScenario.inject(atOnceUsers(1)))

}
