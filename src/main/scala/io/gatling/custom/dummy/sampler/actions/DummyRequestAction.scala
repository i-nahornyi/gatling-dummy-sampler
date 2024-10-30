package io.gatling.custom.dummy.sampler.actions

import io.gatling.commons.stats.OK
import io.gatling.commons.util.Clock
import io.gatling.commons.validation.Validation
import io.gatling.core.action.{Action, RequestAction}
import io.gatling.core.session.{Expression, Session}
import io.gatling.core.stats.StatsEngine
import io.gatling.core.structure.ScenarioContext
import io.gatling.core.util.NameGen

import java.util.concurrent.TimeUnit

class DummyRequestAction(dummyActionName: Expression[String], duration: Expression[Long], ctx: ScenarioContext, nextAction: Action) extends RequestAction with NameGen {


  override def sendRequest(session: Session): Validation[Unit] = for {
    dummyActionName <- requestName(session)
    actionDuration <- duration(session)
  } yield {

    val startTime = clock.nowMillis
    val endTime = startTime + actionDuration

    session.eventLoop.schedule((() => {
      logger.debug(s"Emulate request latency for ${actionDuration}ms")
      statsEngine.logResponse(session.scenario, session.groups, dummyActionName, startTime, endTime, OK, None, None)
      next ! session.logGroupRequestTimings(startTime, endTime)

    }): Runnable, actionDuration, TimeUnit.MILLISECONDS)
  }

  override def clock: Clock = ctx.coreComponents.clock

  override def requestName: Expression[String] = dummyActionName

  override def statsEngine: StatsEngine = ctx.coreComponents.statsEngine

  override def next: Action = nextAction

  override def name: String = genName("dummySampler")
}
