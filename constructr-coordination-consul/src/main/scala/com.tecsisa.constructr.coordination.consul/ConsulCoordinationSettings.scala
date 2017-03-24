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

import akka.actor.ActorSystem

import scala.util.Try

/**
  * Created by domingueza on 24/03/2017.
  */
case class ConsulCoordinationSettings(
  host: String,
  port: Int,
  agentName: String,
  https: Boolean = false,
  httpToken: Option[String] = None
)

object ConsulCoordinationSettings {

  def apply(actorSystem: ActorSystem): ConsulCoordinationSettings = {
    val config = actorSystem.settings.config
    val host = config.getString("constructr.coordination.host")
    val port = config.getInt("constructr.coordination.port")
    val agentName = Try(config.getString("constructr.consul.agent-name"))
      .getOrElse("")
    val https = Try(config.getBoolean("constructr.consul.https"))
      .getOrElse(false)
    val httpToken = Try(config.getString("constructr.consul.http-token")).toOption

    ConsulCoordinationSettings(host, port, agentName, https, httpToken)
  }

}
