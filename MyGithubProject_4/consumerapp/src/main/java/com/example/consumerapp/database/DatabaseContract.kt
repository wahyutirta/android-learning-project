package com.example.consumerapp.database

import android.net.Uri
import android.provider.BaseColumns
import com.example.consumerapp.BuildConfig.TB_NAME


object DatabaseContract {
    const val AUTHORITY = "com.example.mygithubproject"
    const val SCHEME = "content"

    internal class userColumns : BaseColumns {
        companion object {
            const val ID = "id"
            const val USERNAME = "login"
            const val AVATAR_URL = "avatarUrl"
            const val HTML_URL = "htmlUrl"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TB_NAME)
                .build()
        }
    }

}