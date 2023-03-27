package entity

import io.ebean.Model
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DTodo(

    @Id
    val id: String,

    val content: String,



): Model()