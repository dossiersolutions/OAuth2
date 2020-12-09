package com.example.oauth.guest.models

import com.example.oauth.user.models.UserEntity
import javax.persistence.*

@Entity
class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0
    var firstName: String? = null
    var lastName: String? = null
    var description: String? = null
    var invited: String? = null
    var confirmed: String? = null
    var tableNo: String? = null

    @ManyToOne
    var user: UserEntity? = null
}