package com.example.consumerapp.database

import android.database.Cursor
import com.example.consumerapp.model.UsersData

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<UsersData> {
        val list = ArrayList<UsersData>()
        if (null != cursor) {
            while (cursor.moveToNext()) {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavUserColumns.ID))
                val username =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavUserColumns.USERNAME))
                val avatarUrl =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavUserColumns.AVATAR_URL))
                list.add(
                    UsersData(
                        username,
                        id,
                        avatarUrl
                    )
                )
            }
        }
        return list
    }
}