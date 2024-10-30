package io.gatling.custom.dummy.sampler.actions

import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.session.Expression
import io.gatling.core.structure.ScenarioContext

case class DummyActionBuilder(requestName: Expression[String], duration: Expression[Long]) extends ActionBuilder {
  override def build(ctx: ScenarioContext, next: Action): Action = new DummyRequestAction(requestName, duration, ctx, next)
}