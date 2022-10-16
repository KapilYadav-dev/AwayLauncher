package `in`.kay.awaylauncher.domain.mappers

import `in`.kay.awaylauncher.domain.model.AppUsageStats
import android.app.usage.UsageStats

fun List<UsageStats>.toAppUsageStatsList(): List<AppUsageStats> {
    return map {
        AppUsageStats(
            totalTime = it.totalTimeInForeground,
            packageName = it.packageName
        )
    }
}