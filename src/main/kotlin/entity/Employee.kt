package entity
import io.ebean.Model
import io.ebean.Finder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Employee (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val firstName: String,

    val lastName: String,

    val address: String? = null
) : Model() {
    companion object Find : Finder<Long, Employee>(Employee::class.java)
}



