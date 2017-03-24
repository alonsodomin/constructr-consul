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
