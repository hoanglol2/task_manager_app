package com.example.roomproject

import androidx.room.*
import com.example.roomproject.entities.*
import com.example.roomproject.relations.PlaylistWithSongs
import com.example.roomproject.relations.SongWithPlaylists
import com.example.roomproject.relations.UserWithLibrary
import com.example.roomproject.relations.UserWithPlaylists

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLibrary(lib: Library)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: Playlist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylistSongCrossRef(crossRef: PlaylistSongCrossRef)

    @Transaction
    @Query("SELECT * FROM User")
    suspend fun getUsersAndLibraries(): List<UserWithLibrary>

    @Transaction
    @Query("SELECT * FROM User")
    suspend fun getUsersWithPlaylists(): List<UserWithPlaylists>

    @Transaction
    @Query("SELECT * FROM playlist")
    suspend fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("SELECT * FROM song")
    suspend fun getSongsWithPlaylists(): List<SongWithPlaylists>
}