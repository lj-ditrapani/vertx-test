package info.ditrapani

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.client.WebClient
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import io.vertx.kotlin.ext.web.client.sendAwait
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val PORT = 44778

class Server(val mockHost: String) : CoroutineVerticle() {
    override suspend fun start() {
        val client = WebClient.create(vertx)
        val router = Router.router(vertx)
        router.get("/test/:delay").handler { routingContext ->
            GlobalScope.launch(vertx.dispatcher()) {
                val delay = routingContext.request().getParam("delay")
                val response = client.get(44779, mockHost, "/test/$delay").sendAwait()
                routingContext.response().setStatusCode(response.statusCode()).end(response.body())
            }
        }
        val server = vertx.createHttpServer().requestHandler(router)
        server.listenAwait(PORT)
        println("Test server listening on port $PORT")
    }
}

fun main() {
    Vertx.vertx().deployVerticle(Server("localhost"))
}
