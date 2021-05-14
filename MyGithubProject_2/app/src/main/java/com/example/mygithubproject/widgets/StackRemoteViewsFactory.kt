package com.example.mygithubproject.widgets

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Binder
import android.provider.BaseColumns
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mygithubproject.R
import com.example.mygithubproject.services.data.UsersData
import java.util.*


internal class StackRemoteViewsFactory(private val mContext: Context) :
    RemoteViewsService.RemoteViewsFactory {

    // dbScheme = "content" | dbAuthority = "com.example"
    private val tbName = "tb_favUsers"
    private val dbContentUri = "content://com.example"
    private val usersContentUri = "$dbContentUri/$tbName"

    private var list: List<UsersData> = listOf()

    private var cursor: Cursor? = null

    internal class FavUsersColumns : BaseColumns {
        companion object {
            const val ID = "id"
            const val USERNAME = "login"
            const val AVATAR_URL = "avatarUrl"
        }
    }

    override fun onCreate() {}

    private fun Cursor.mapCursorToArrayList(): ArrayList<UsersData> {
        val list = ArrayList<UsersData>()
        while (moveToNext()) {
            val id =
                getInt(getColumnIndexOrThrow(FavUsersColumns.ID))
            val username =
                getString(getColumnIndexOrThrow(FavUsersColumns.USERNAME))
            val avatarUrl =
                getString(getColumnIndexOrThrow(FavUsersColumns.AVATAR_URL))
            list.add(
                UsersData(
                    username,
                    id,
                    avatarUrl
                )
            )
        }
        return list
    }

    override fun onDataSetChanged() {

        cursor?.close()
        val identifyToken = Binder.clearCallingIdentity()

        cursor = mContext.contentResolver?.query(usersContentUri.toUri(), null, null, null, null)
        cursor?.let {
            list = it.mapCursorToArrayList()
        }

        Binder.restoreCallingIdentity(identifyToken)

    }

    override fun onDestroy() {

        cursor?.close()
        list = listOf()

    }

    override fun getCount(): Int = list.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)

        if (!list.isNullOrEmpty()) {
            // Set item stack view
            rv.apply {
                list[position].apply {
                    setImageViewBitmap(
                        R.id.widget_image_view, avatar_url?.toBitmap(mContext)
                    )
                    setTextViewText(
                        R.id.widget_username, login ?: login
                    )
                    setTextViewText(
                        R.id.widget_user_id, id.toString()
                    )
                }
            }
        }
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

    private fun String?.toBitmap(context: Context): Bitmap {
        var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_default)

        try {
            Glide.with(context)
                .setDefaultRequestOptions(RequestOptions().error(R.drawable.ic_default).diskCacheStrategy(DiskCacheStrategy.ALL))
                .asBitmap()
                .load(this)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {}
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        bitmap = resource
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

}


