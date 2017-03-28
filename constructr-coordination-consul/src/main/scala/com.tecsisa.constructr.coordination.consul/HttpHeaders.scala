/*
 * Copyright 2016 TECNOLOGIA, SISTEMAS Y APLICACIONES S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tecsisa.constructr.coordination.consul

import akka.http.scaladsl.model.headers.{ModeledCustomHeader, ModeledCustomHeaderCompanion}

import scala.util.{Success, Try}

/**
  * Created by domingueza on 24/03/2017.
  */
private[consul] object HttpHeaders {

  final case class ConsulToken(value: String) extends ModeledCustomHeader[ConsulToken] {
    override def companion: ModeledCustomHeaderCompanion[ConsulToken] = ConsulToken

    override def renderInRequests: Boolean = true

    override def renderInResponses: Boolean = false
  }

  object ConsulToken extends ModeledCustomHeaderCompanion[ConsulToken] {
    override def name: String = "X-Consul-Token"

    override def parse(value: String): Try[ConsulToken] =
      Success(ConsulToken(value))
  }

}
