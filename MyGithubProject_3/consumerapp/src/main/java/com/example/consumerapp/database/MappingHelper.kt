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
                val login =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavUserColumns.USERNAME))
                val avatar_url =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavUserColumns.AVATAR_URL))
                list.add(
                    UsersData(
                        login,
                        id,
                        avatar_url
                    )
                )
            }
        }
        return list
    }
}