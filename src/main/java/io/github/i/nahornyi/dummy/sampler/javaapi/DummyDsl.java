package io.github.i.nahornyi.dummy.sampler.javaapi;

import io.gatling.commons.stats.Status;
import io.gatling.javaapi.core.Session;
import io.github.i.nahornyi.dummy.sampler.javaapi.actions.DummyActionBuilder;

import java.util.function.Function;

import static io.gatling.javaapi.core.internal.Expressions.*;

public final class DummyDsl {

    private DummyDsl() {
    }
    public static DummyActionBuilder dummySampler(String name, String duration, Status status) {
        return new DummyActionBuilder(io.github.i.nahornyi.dummy.sampler.DummyDsl.dummySampler(toStringExpression(name), toExpression(duration, Long.class), status));
    }

    public static DummyActionBuilder dummySampler(Function<Session, String> name, Function<Session, Long> duration, Status status) {
        return new DummyActionBuilder(io.github.i.nahornyi.dummy.sampler.DummyDsl.dummySampler(javaFunctionToExpression(name), javaLongFunctionToExpression(duration), status));
    }

    public static DummyActionBuilder dummySampler(String name, Function<Session, Long> duration, Status status) {
        return new DummyActionBuilder(io.github.i.nahornyi.dummy.sampler.DummyDsl.dummySampler(toStringExpression(name), javaLongFunctionToExpression(duration), status));
    }

    public static DummyActionBuilder dummySampler(Function<Session, String> name, String duration, Status status) {
        return new DummyActionBuilder(io.github.i.nahornyi.dummy.sampler.DummyDsl.dummySampler(javaFunctionToExpression(name), toExpression(duration, Long.class), status));
    }

    public static DummyActionBuilder dummySampler(String name, String duration) {
        return dummySampler(name, duration, OK);
    }

    public static DummyActionBuilder dummySampler(Function<Session, String> name, Function<Session, Long> duration) {
        return dummySampler(name, duration, OK);
    }

    public static DummyActionBuilder dummySampler(String name, Function<Session, Long> duration) {
        return dummySampler(name, duration, OK);
    }

    public static DummyActionBuilder dummySampler(Function<Session, String> name, String duration){
        return dummySampler(name, duration, OK);
    }

    public static final Status KO = Status.apply("KO");
    public static final Status OK = Status.apply("OK");
}

