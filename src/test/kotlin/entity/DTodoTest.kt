package entity

import entity.query.QDTodo
import kotlin.test.assertEquals

class DTodoTest(


) {
    @org.junit.jupiter.api.Test
    fun `check the DTodo Db works`() {
        val myTodo = DTodo(id = "12", content = "testing")
        myTodo.save()

        val findOne = QDTodo().content.eq("finish assignment").findOne()

        assertEquals(myTodo.content, findOne!!.content)

    }
}