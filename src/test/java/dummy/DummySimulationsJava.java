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

package dummy;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.util.concurrent.ThreadLocalRandom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.github.i.nahornyi.dummy.sampler.javaapi.DummyDsl.KO;
import static io.github.i.nahornyi.dummy.sampler.javaapi.DummyDsl.dummySampler;

public class DummySimulationsJava extends Simulation {

    ScenarioBuilder mainScenario = scenario("mocked_scenario").repeat(5).on(
            group("dummyTransaction").on(
                    pause(1),
                    dummySampler("dummyRequestA", "#{randomLong(250,1000)}"),
                    pause(1, 5),
                    exec(session -> session.set("responseTime", 200)),
                    dummySampler(session -> "dummyRequestB", session -> session.getLong("responseTime")),
                    pause(1, 5),
                    exec(session -> session.set("requestName", "dummyRequestC")),
                    dummySampler("#{requestName}", session -> ThreadLocalRandom.current().nextLong(100, 500)),
                    pause(1, 5),
                    dummySampler(session -> "dummyRequestD", "#{randomLong(250,1000)}"),
                    randomSwitch().on(
                            percent(90).then(dummySampler("dummyRequestE", "#{randomLong(250,1000)}")),
                            percent(10).then(dummySampler("dummyRequestE", "#{randomLong(250,1000)}", KO)
                            )
                    )
            )
    );

    {
        setUp(mainScenario.injectOpen(rampUsers(10).during(30)));
    }
}
