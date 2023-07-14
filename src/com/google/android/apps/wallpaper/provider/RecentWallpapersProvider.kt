package com.google.android.apps.wallpaper.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.android.systemui.flags.FlagManager

class RecentWallpapersProvider : ContentProvider() {

    override fun onCreate(): Boolean = true

    override fun getType(uri: Uri): String = "vnd.android.cursor.dir/recent_wallpapers"

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        if (uri.path != "/list_recent") {
            return null
        }
        return MatrixCursor(arrayOf(
            FlagManager.EXTRA_NAME,
            "placeholder_color",
            "component",
            "title",
            "last_updated"
        ))
    }

    override fun update(
        uri: Uri,
        contentValues: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return if (uri.path == "/set_recent_wallpaper") 1 else 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? = null
}
