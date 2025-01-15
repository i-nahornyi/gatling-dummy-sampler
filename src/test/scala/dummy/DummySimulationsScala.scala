/*
 * Copyright 2024-2025 Ivan Nahornyi (ivan.nahornyi.93@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
