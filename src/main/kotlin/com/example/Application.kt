package com.example

import com.example.plugins.configureRouting
import entity.DTodo
import entity.query.QDTodo
import io.ebean.DB
import io.ebean.annotation.Platform
import io.ebean.dbmigration.DbMigration
import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.resources.Resources

fun main() {

    DB.getDefault()
    DbMigration.create().apply {
        setPlatform(Platform.POSTGRES)
    }.generateMigration()

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

class HashDatabase() : Database {
    val myHashMap = mutableMapOf<String, String>()

    override fun addToHash(key: String, value: String) {
        myHashMap[key] = value
    }

    override fun removeFromHash(key: String) {
        myHashMap.remove(key, myHashMap[key])
    }

    override fun returnValueWithKey(key: String): String? {
        return myHashMap[key]
    }

    override fun hasKey(key: String): Boolean {
        return (myHashMap.containsKey(key))
    }
}

interface Database {
    fun hasKey(key: String): Boolean
    fun returnValueWithKey(key: String): String?
    fun addToHash(key: String, value: String)
    fun removeFromHash(key: String)
}

class EbeansDb() : Database {
    override fun hasKey(key: String): Boolean {
        val dToDo = QDTodo().id.iequalTo(key).findOne()
        return dToDo != null
    }

    override fun returnValueWithKey(key: String): String? {
        val dToDo = QDTodo().id.iequalTo(key).findOne()
        return dToDo?.content
    }

    override fun addToHash(key: String, value: String) {
        val dTodo = DTodo(id = key, content = value)
        dTodo.save()
    }

    override fun removeFromHash(key: String) {
        QDTodo().id.iequalTo(key).delete()
    }

}

