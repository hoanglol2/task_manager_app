package com.example.roomproject.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomproject.entities.Playlist
import com.example.roomproject.entities.PlaylistSongCrossRef
import com.example.roomproject.entities.Song

data class SongWithPlaylists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playlistId",
        // xac dinh thuc the tham chieu cheo , mqh giua song & playlist
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>
)