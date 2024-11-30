package io.github.i.nahornyi.dummy.sampler.javaapi.actions;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ChainBuilder;

public class DummyActionBuilder implements ActionBuilder {

    private final io.github.i.nahornyi.dummy.sampler.actions.DummyActionBuilder wrapped;

    public DummyActionBuilder(io.github.i.nahornyi.dummy.sampler.actions.DummyActionBuilder wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public io.gatling.core.action.builder.ActionBuilder asScala() {
        return wrapped;
    }

    @Override
    public ChainBuilder toChainBuilder() {
        return ActionBuilder.super.toChainBuilder();
    }
}
