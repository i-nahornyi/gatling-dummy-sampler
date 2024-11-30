package io.github.i.nahornyi.dummy.sampler.actions

import io.gatling.core.Predef.Status
import io.gatling.core.action.Action
import io.gatling.core.session.Session
import io.gatling.core.structure.ScenarioContext

trait ActionBase {

  val ctx: ScenarioContext

  protected def executeNext(session: Session, startTime: Long, endTime: Long, status: Status, next: Action, requestName: String, responseCode: Option[String], message: Option[String], isCrashed: Boolean = false): Unit = {

    if (!isCrashed) {
      ctx.coreComponents.statsEngine.logResponse(session.scenario, session.groups, requestName, startTime, endTime, status, responseCode, message)
    }
    else {
      ctx.coreComponents.statsEngine.logRequestCrash(session.scenario, session.groups, requestName, message.get)
    }
    next ! session.logGroupRequestTimings(startTime, endTime)
  }

}
