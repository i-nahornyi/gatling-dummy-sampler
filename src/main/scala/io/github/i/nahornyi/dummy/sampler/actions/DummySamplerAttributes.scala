package io.github.i.nahornyi.dummy.sampler.actions

import io.gatling.commons.stats.Status
import io.gatling.core.session.Expression


object DummySamplerAttributes {
  def apply(
             actionName: Expression[String],
             duration: Expression[Long],
             status: Option[Status]
           ): DummySamplerAttributes = new DummySamplerAttributes(
    actionName, duration, status
  )
}

final case class DummySamplerAttributes(actionName: Expression[String], duration: Expression[Long], status: Option[Status])
