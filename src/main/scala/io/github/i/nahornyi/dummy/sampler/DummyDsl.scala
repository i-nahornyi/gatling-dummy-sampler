/*
 * Copyright 2024-2025 Ivan Nahornyi (ivan.nahornyi.93@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
