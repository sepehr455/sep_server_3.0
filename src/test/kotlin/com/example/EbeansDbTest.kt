package com.example

import entity.DTodo
import entity.query.QDTodo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EbeansDbTest {
    val ebeansDb = EbeansDb()

    @Test
    fun hasKey() {
        val testKey = "testing"
        val hasKey = ebeansDb.hasKey("testing")
        assertEquals(hasKey, false)
    }

    @Test
    fun hasKey2() {
        val todo = DTodo(id = "12", content = "testing")
        todo.save()
        val hasKey = ebeansDb.hasKey("12")
        assertEquals(hasKey, true)
    }

    @Test
    fun retrunsValue() {
        val todo = DTodo(id = "13", content = "testing")
        todo.save()
        val resultValue = ebeansDb.returnValueWithKey("13")
        assertEquals(resultValue, "testing")
    }

    @Test
    fun addingToHashWorks() {
        ebeansDb.addToHash("14", "testing")
        val qdTodo = QDTodo().id.iequalTo("14").findOne()
        assertEquals(qdTodo!!.content, "testing")

    }

    @Test
    fun deleteWorks() {
        val todo = DTodo(id = "13", content = "testing")
        todo.save()
        ebeansDb.removeFromHash("13")
        val result = QDTodo().id.iequalTo("13").findOne()
        assertEquals(result, null)
    }
}