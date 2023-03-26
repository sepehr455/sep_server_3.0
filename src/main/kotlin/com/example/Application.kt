package com.example

import entity.Employee
import com.example.plugins.configureRouting
import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.resources.Resources

fun main() {

    val employee_22 = Employee(22, "sepehr", "address")
    employee_22.save()
    val foundEmployee = Employee.Find.byId(22)
    if (foundEmployee != null){
        println("employee with ID ${foundEmployee.id} was found and their name is ${foundEmployee.firstName}}")
    } else{
        println("employee was not found")
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}

fun Application.module() {
    install(Resources)
    configureRouting()
}

@Resource("/todo/{key}")
class ToDo(val key: String) {
}

class Database() {
    val myHashMap = mutableMapOf<String, String>()

    fun addToHash(key: String, value: String) {
        myHashMap[key] = value
    }

    fun removeFromHash(key: String) {
        myHashMap.remove(key, myHashMap[key])
    }

    fun returnValueWithKey(key: String): String? {
        return myHashMap[key]
    }

    fun hasKey(key: String): Boolean {
        return (myHashMap.containsKey(key))
    }
}


