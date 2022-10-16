package `in`.kay.awaylauncher.domain.model

import android.graphics.drawable.Drawable

data class AppInfo(
    var appName: String? = null,
    var packageName: String? = null,
    var appIcon: Drawable? = null
)
