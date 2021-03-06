package com.example.mygithubproject.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.example.mygithubproject.R


class FavAppWidget : AppWidgetProvider() {


    companion object {

        private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val intent = Intent(context, StackWidgetServices::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            val intentToast = Intent(context, FavAppWidget::class.java)
            intentToast.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            val views = RemoteViews(context.packageName, R.layout.fav_app_widget)
            views.setRemoteAdapter(R.id.stackV, intent)
            views.setEmptyView(R.id.stackV, R.id.emptyV)

            val pendingIntentToast = PendingIntent.getBroadcast(context, 0, intentToast, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.stackV, pendingIntentToast)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        intent.let {
            if (it.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
                val component = ComponentName(
                    context,
                    FavAppWidget::class.java
                )
                AppWidgetManager.getInstance(context).apply {
                    notifyAppWidgetViewDataChanged(
                        getAppWidgetIds(component),
                        R.id.stackV
                    )
                }
            }
        }
        super.onReceive(context, intent)
    }
}

