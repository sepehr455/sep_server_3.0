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

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(nullable = true)
    var address: String? = null
) : Model() {

    companion object Find : Finder<Long, Employee>(Employee::class.java)
}



