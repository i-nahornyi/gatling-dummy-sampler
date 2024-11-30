package io.github.i.nahornyi.dummy.sampler

import io.gatling.commons.stats.Status
import io.gatling.core.session.Expression
import io.github.i.nahornyi.dummy.sampler.actions.{DummyActionBuilder, DummySamplerAttributes}

object DummyDsl {

  def dummySampler(requestName: Expression[String],
                   duration: Expression[Long],
                   status: Status = DummyDsl.OK,
                  ): DummyActionBuilder = actions.DummyActionBuilder(DummySamplerAttributes(requestName, duration, Option(status))
  )

  def KO: Status = {
    io.gatling.commons.stats.KO
  }

  def OK: Status = {
    io.gatling.commons.stats.OK
  }
}
