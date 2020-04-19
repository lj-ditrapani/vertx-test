package info.ditrapani

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle

private const val PORT = 44770

class Server : CoroutineVerticle() {
    override suspend fun start() {
        val router = Router.router(vertx)
        router.get("/test/:delay").handler { routingContext ->
            val delay = routingContext.request().getParam("delay").toLong()
            val sendResponse: (Long) -> Unit = { routingContext.response().end("OK") }
            vertx.setTimer(delay, sendResponse)
        }
        val server = vertx.createHttpServer().requestHandler(router)
        server.listenAwait(PORT)
        println("Listening on port $PORT")
    }
}

fun main() {
    Vertx.vertx().deployVerticle(Server())
}
