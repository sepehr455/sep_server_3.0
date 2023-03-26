package com.example.plugins


import com.example.Database
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

fun Application.configureRouting() {

    val database = Database()

    routing {
        get<ToDo> {
            val reqKey: String = it.key

            if(database.hasKey(reqKey)){
                val desiredValue = database.returnValueWithKey(reqKey)
                call.respondText { "The value for key $reqKey is: $desiredValue" }
            }else{
                call.respond(HttpStatusCode.NotFound, "Key not found")
            }
        }

        post<ToDo> {

            val currentKey: String = it.key
            val currentvalue: String = call.receiveText()
            database.addToHash(currentKey, currentvalue)
            call.respondText("Success")

        }

        delete<ToDo> {

            val currentKey: String = it.key
            if(database.hasKey(currentKey)){
                database.removeFromHash(currentKey)
                call.respondText("Success")
            }else{
                call.respond(HttpStatusCode.NotFound, "Key not found")
            }
        }
    }
}
