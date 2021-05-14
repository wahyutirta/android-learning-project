package com.example.mygithubproject.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.mygithubproject.services.data.DBUser
import com.example.mygithubproject.services.data.FavUsersDao

class FavoriteContentProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.example.mygithubproject"
        private const val TABLE_NAME = "tb_favUsers"
        const val ID_FAVORITE = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, ID_FAVORITE)
        }
    }

    private lateinit var Dao: FavUsersDao

    override fun onCreate(): Boolean {
        Dao = context?.let { ctx ->
            DBUser.getDatabase(ctx)?.favoriteUsersDao()
        }!!
        return false

    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }


    override fun update(
            uri: Uri,
            values: ContentValues?,
            selection: String?,
            selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun query(
            uri: Uri, projection: Array<String>?, selection: String?,
            selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when (uriMatcher.match(uri)) {
            ID_FAVORITE -> {
                cursor = Dao.findAllUser()
                if (null != context) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return cursor
    }


}