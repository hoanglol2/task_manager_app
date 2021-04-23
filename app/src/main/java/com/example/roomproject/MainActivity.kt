package com.example.roomproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.roomproject.ui.CreateTaskFragment
import com.example.roomproject.ui.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            add(android.R.id.content, HomeFragment(), "HomeFragment")
            commit()
        }

        // bottom tab navigation
        btnNavDashBoard.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> {
                }
                R.id.miCalendar -> {
                }
                R.id.miCreateTask -> {
                    supportFragmentManager.beginTransaction().apply {
                        add(android.R.id.content, CreateTaskFragment(), "CreateTaskFragment")
                        addToBackStack(null)
                        commit()
                    }
                }
                R.id.miUser -> {
                }
                R.id.miSetting -> {
                }
            }
            true
        }


        /*val musicDao = MusicDatabase.getDatabase(this).musicDao()

        val users = listOf(
            User(1, "Nguyen Hoa", 23),
            User(2, "Nguyen Ha", 11),
            User(3, "Ha Lan", 32),
            User(4, "Tu Quynh", 22),
            User(5, "Bao Tram", 25)
        )

        val libraries = listOf(
            Library(1, 1),
            Library(2, 2),
            Library(3, 3),
            Library(4, 4),
            Library(5, 5)
        )

        val playlists = listOf(
            Playlist(1, 1, "Nhac co dien"),
            Playlist(2, 1, "Nhac hien dai"),
            Playlist(3, 1, "Nhac cach mang"),
            Playlist(4, 1, "Nhac tre"),
            Playlist(5, 1, "Nhac song ha tay")
        )

        val songs = listOf(
            Song(1, "The Sky", "Son tung MTP"),
            Song(2, "Hahalu", "Nguyen Duy"),
            Song(3, "Con duong mua", "Cao Thai Son"),
            Song(4, "Cau Vong Khuyet", "Khanh Phuong"),
            Song(5, "ABC", "Tra Long")
        )

        val playlistSongRelations = listOf(
            PlaylistSongCrossRef(1, 1),
            PlaylistSongCrossRef(2, 2),
            PlaylistSongCrossRef(2, 3),
            PlaylistSongCrossRef(1, 4),
            PlaylistSongCrossRef(1, 5)
        )



        lifecycleScope.launch {
            users.forEach { musicDao.insertUser(it) }
            libraries.forEach { musicDao.insertLibrary(it) }
            playlists.forEach { musicDao.insertPlaylist(it) }
            songs.forEach { musicDao.insertSong(it) }
            playlistSongRelations.forEach { musicDao.insertPlaylistSongCrossRef(it) }

            val usersList = musicDao.getUsersAndLibraries().map { it.library }
            val usersWithPlaylist = musicDao.getUsersWithPlaylists().map { it.playlists }
            val playlistsWithSongs = musicDao.getPlaylistsWithSongs().map { it.songs }
            val songWithPlaylists = musicDao.getSongsWithPlaylists().map { it.playlists }

            Log.d("Main", "playlistsWithSongs: ${playlistsWithSongs.toString()}")
            Log.d("Main", "songWithPlaylists: ${songWithPlaylists.toString()}")
        }
        */
    }

    fun addFragment(
        fragment: Fragment,
        isCanBack: Boolean,
        fragmentTag: String
    ) {
        supportFragmentManager.beginTransaction().apply {
            add(android.R.id.content, fragment, fragmentTag)
            if (isCanBack) addToBackStack(null)
            commit()
        }
    }

    fun togglePassword(isHidePassword: Boolean, param: View) {
        param.visibility = if (isHidePassword) View.GONE else View.VISIBLE
    }

    fun destroyAllFragment() {
        /*            supportFragmentManager.fragments.forEach { fragment ->
                supportFragmentManager.findFragmentById(android.R.id.content)?.let {
                    supportFragmentManager.beginTransaction().remove(
                        it
                    ).commitNow()
                    supportFragmentManager.popBackStack()
                }
            }*/

        supportFragmentManager.apply {
            val fragmentHomeFragment = findFragmentByTag("HomeFragment")
            val fragmentSignUpFragment = findFragmentByTag("SignInFragment")
            fragmentHomeFragment?.let {
                commit {
                    popBackStack()
                    remove(it)
                }
            }
            fragmentSignUpFragment?.let {
                commit {
                    popBackStack()
                    remove(it)
                }
            }
        }
    }
}