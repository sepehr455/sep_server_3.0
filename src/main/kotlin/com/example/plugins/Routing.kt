package com.example.plugins

import com.example.EbeansDb
import com.example.ToDo
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing


//handle the case where posting a todo throws a 500 if you post the same todo twice
//remove the two databse calls in delete (turn it just into 1 call) --> still say if the key is not found


fun Application.configureRouting() {

    val ebeansDb = EbeansDb()

    routing {
        get<ToDo> {
            val reqKey: String = it.key
            val desiredValue = ebeansDb.returnValueWithKey(reqKey)
            if (desiredValue != null) {
                call.respondText { "The value for key $reqKey is: $desiredValue" }
            } else {
                call.respond(HttpStatusCode.NotFound, "Key not found")
            }
        }

        post<ToDo> {
            val currentKey: String = it.key
            val currentvalue: String = call.receiveText()
            ebeansDb.addToHash(currentKey, currentvalue)
            call.respondText("Success")
        }

        delete<ToDo> {
            val currentKey: String = it.key
            if (ebeansDb.hasKey(currentKey)) {
                ebeansDb.removeFromHash(currentKey)
                call.respondText("Success")
            } else {
                call.respond(HttpStatusCode.NotFound, "Key not found")
            }
        }
    }
}
