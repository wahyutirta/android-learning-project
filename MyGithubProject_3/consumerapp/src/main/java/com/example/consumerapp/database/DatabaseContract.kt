package com.example.consumerapp.database

import android.net.Uri
import android.provider.BaseColumns


object DatabaseContract {
    const val AUTHORITY = "com.example.mygithubproject"
    const val SCHEME = "content"

    internal class FavUserColumns : BaseColumns {
        companion object {
            private const val TABLE_NAME = "tb_favUsers"
            const val ID = "id"
            const val USERNAME = "login"
            const val AVATAR_URL = "avatarUrl"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)//cek wa
                .build()
        }
    }

}