package io.github.i.nahornyi.dummy.sampler.actions

import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.structure.ScenarioContext

final case class DummyActionBuilder(attributes: DummySamplerAttributes) extends ActionBuilder {
  override def build(ctx: ScenarioContext, next: Action): Action = DummyRequestAction(attributes, ctx, next)
}