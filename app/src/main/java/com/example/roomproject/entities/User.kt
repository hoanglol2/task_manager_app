package com.example.roomproject.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = false) val userId: Long,
    val fullName: String,
    val phone: Int,
    val email: String,
    val password: String
)