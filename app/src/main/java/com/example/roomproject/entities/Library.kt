package com.example.roomproject.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Library(
    @PrimaryKey(autoGenerate = false) val libraryId: Long,
    val userOwnerId: Long
)