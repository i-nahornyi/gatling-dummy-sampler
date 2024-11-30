package io.github.i.nahornyi.dummy.sampler.actions

import io.gatling.commons.util.Clock
import io.gatling.commons.validation.Validation
import io.gatling.core.action.{Action, RequestAction}
import io.gatling.core.session.{Expression, Session}
import io.gatling.core.stats.StatsEngine
import io.gatling.core.structure.ScenarioContext
import io.gatling.core.util.NameGen
import io.github.i.nahornyi.dummy.sampler.DummyDsl

import java.util.concurrent.TimeUnit

case class DummyRequestAction(attributes: DummySamplerAttributes, ctx: ScenarioContext, nextAction: Action)
  extends RequestAction with ActionBase with NameGen {


  override def sendRequest(session: Session): Validation[Unit] = for {
    dummyActionName <- requestName(session)
    actionDuration <- attributes.duration(session)
  } yield {

    val startTime = clock.nowMillis
    val endTime = startTime + actionDuration
    val errorMessage = if (attributes.status.getOrElse(DummyDsl.OK) == DummyDsl.OK) None else Option("DummyErrorMessage")

    session.eventLoop.schedule((() => {
      logger.debug(s"request latency ${actionDuration}ms for ${dummyActionName} emulated")
      executeNext(session, startTime, endTime, attributes.status.getOrElse(DummyDsl.OK), nextAction, dummyActionName, None, errorMessage)
    }): Runnable, actionDuration, TimeUnit.MILLISECONDS)
  }

  override def clock: Clock = ctx.coreComponents.clock

  override def requestName: Expression[String] = attributes.actionName

  override def statsEngine: StatsEngine = ctx.coreComponents.statsEngine

  override def next: Action = nextAction

  override def name: String = genName("dummySampler")
}
