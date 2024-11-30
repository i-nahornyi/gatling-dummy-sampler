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
