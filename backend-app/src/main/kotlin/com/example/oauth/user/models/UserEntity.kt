package com.example.oauth.user.models

import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "my_users")
class UserEntity {
    @Id
    var id = 0;
    var name = "";
    var email = "";
    var password = "";

    @ElementCollection
    @Column(name="role")
    @JvmSuppressWildcards
    var grantedAuthoritiesList: Collection<GrantedAuthority> = ArrayList<GrantedAuthority>();

    constructor(id: Int, email: String, name: String){
        this.id = id;
        this.email = email;
        this.name = name;
    }

    constructor(){}
}