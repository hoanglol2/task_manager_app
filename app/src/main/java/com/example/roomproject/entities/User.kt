package com.example.roomproject.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long,
    val fullName: String,
    val phone: Long,
    val email: String,
    val password: String
) {
    constructor(fullName: String, phone: Long, email: String, password: String) : this(
        0,
        fullName,
        phone,
        email,
        password
    )
}

