package com.github.jw3.geo

import akka.actor.ActorRef
import akka.http.scaladsl.model.ws.TextMessage
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.{Flow, Sink}
import com.github.jw3.geo.Api.Events.PositionUpdate

object EventRoutes {
  case class Mute(ref: ActorRef)
}

trait EventRoutes {
  import akka.http.scaladsl.server.Directives._

  def eventRoutes(journaler: ActorRef): Route =
    pathPrefix("api") {
      extractActorSystem { implicit system ⇒
        path("watch" / "device") {
          get {
            extractUpgradeToWebSocket { upgrade ⇒
              complete {
                val source = Streams.readJournal()
                  .map(_.event)
                  .filter {
                    case PositionUpdate(_, _) ⇒ true
                    case _ ⇒ false
                  }
                  .map(_.asInstanceOf[PositionUpdate])
                  .map(pu ⇒ s"${pu.device}:${pu.pos.x}:${pu.pos.y}")
                  .map(TextMessage(_))
                upgrade.handleMessages(Flow.fromSinkAndSource(Sink.ignore, source))
              }
            }
          }
        }
      }
    }
}
