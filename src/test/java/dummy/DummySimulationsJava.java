package dummy;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.custom.dummy.sampler.javaapi.DummyDsl.dummySampler;
import static io.gatling.javaapi.core.CoreDsl.*;

public class DummySimulationsJava extends Simulation {

    ScenarioBuilder mainScenario = scenario("mocked_scenario").repeat(5).on(
            group("flow-a").on(
                    exec(session -> session.set("requestName","requestC").set("respTime",200)),
                    dummySampler("requestA", "#{randomLong(250,1000)}"),
                    pause(1, 5),
                    dummySampler(session -> "requestB", session -> session.getLong("respTime")),
                    pause(1, 5),
                    dummySampler("#{requestName}", session -> (long) 500),
                    pause(1, 5),
                    dummySampler(session -> "requestD", "#{randomLong(250,1000)}"),
                    pause(1, 5)
            )

    );

    {
        setUp(mainScenario.injectOpen(atOnceUsers(1)));
    }
}
