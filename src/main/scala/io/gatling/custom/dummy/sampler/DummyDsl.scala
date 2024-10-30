package io.gatling.custom.dummy.sampler

import io.gatling.core.session.Expression
import io.gatling.custom.dummy.sampler.actions.DummyActionBuilder

trait DummyDsl {

  def dummySampler(requestName: Expression[String],duration: Expression[Long]): DummyActionBuilder = DummyActionBuilder(requestName,duration)
}
