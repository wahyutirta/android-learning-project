package com.example.mygithubproject.widgets

import android.content.Intent
import android.widget.RemoteViewsService

class StackWidgetServices : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        StackRemoteViewsFactory(this.applicationContext)
}