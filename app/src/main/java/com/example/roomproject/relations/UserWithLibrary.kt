package com.example.roomproject.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomproject.entities.Library
import com.example.roomproject.entities.User

data class UserWithLibrary(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val library: Library? = null
)