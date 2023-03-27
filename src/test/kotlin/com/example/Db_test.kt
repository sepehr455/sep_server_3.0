package com.example

import entity.Employee
import junit.framework.TestCase.assertEquals
import org.junit.Test


class Db_test {
    @Test
    fun `test Ebean with Postgres`() {
        val employee_22 = Employee( firstName = "sepehr", lastName =  "address")
        employee_22.save()
        val foundEmployee = Employee.Find.all().first()
        assertEquals(employee_22.firstName, foundEmployee.firstName)
    }
}