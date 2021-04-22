package com.example.roomproject.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomproject.entities.Playlist
import com.example.roomproject.entities.PlaylistSongCrossRef
import com.example.roomproject.entities.Song

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        // xac dinh thuc the tham chieu cheo , mqh giua song & playlist
        associateBy = Junction(PlaylistSongCrossRef::class)

    )
    val songs: List<Song>
)