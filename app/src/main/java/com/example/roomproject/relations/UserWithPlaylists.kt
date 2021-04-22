package com.example.roomproject.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomproject.entities.Playlist
import com.example.roomproject.entities.User

data class UserWithPlaylists(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val playlists: List<Playlist>
)