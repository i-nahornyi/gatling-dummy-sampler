package io.gatling.custom.dummy.sampler.javaapi;

import io.gatling.custom.dummy.sampler.Predef;
import io.gatling.custom.dummy.sampler.javaapi.actions.DummyActionBuilder;
import io.gatling.javaapi.core.Session;

import java.util.function.Function;

import static io.gatling.javaapi.core.internal.Expressions.*;

public final class DummyDsl {

    private DummyDsl() {
    }


    public static DummyActionBuilder dummySampler(String name, String duration) {
        return new DummyActionBuilder(Predef.dummySampler(toStringExpression(name), toExpression(duration, Long.class)));
    }

    public static DummyActionBuilder dummySampler(Function<Session, String> name, Function<Session, Long> duration){
        return new DummyActionBuilder(Predef.dummySampler(javaFunctionToExpression(name), javaLongFunctionToExpression(duration)));
    }

    public static DummyActionBuilder dummySampler(String name, Function<Session, Long> duration){
        return new DummyActionBuilder(Predef.dummySampler(toStringExpression(name), javaLongFunctionToExpression(duration)));
    }
    public static DummyActionBuilder dummySampler(Function<Session, String> name, String duration){
        return new DummyActionBuilder(Predef.dummySampler(javaFunctionToExpression(name), toExpression(duration,Long.class)));
    }

}
