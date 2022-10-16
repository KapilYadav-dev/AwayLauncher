package `in`.kay.awaylauncher.presentation

import `in`.kay.awaylauncher.domain.mappers.toAppUsageStatsList
import `in`.kay.awaylauncher.domain.model.AppInfo
import `in`.kay.awaylauncher.domain.model.AppUsageStats
import `in`.kay.awaylauncher.utils.Utils.capitalise
import `in`.kay.awaylauncher.utils.Utils.ordinalOf
import android.app.AppOpsManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext application: Context) : ViewModel() {

    var appUsageList = mutableListOf<AppUsageStats>()
    var appsList = mutableListOf<AppInfo>()

    private val timeFormat = SimpleDateFormat("hh:mm")
    private val amPmFormat = SimpleDateFormat("aa")
    private var dayFormat = SimpleDateFormat("EEEE")
    private var dateFormat = SimpleDateFormat("dd")
    private var monthFormat = SimpleDateFormat("MMMM")

    init {
        getAppList(application.applicationContext)
        when (checkUsagePermission(application)) {
            true -> {
                fetchUsageStats(application.applicationContext)
            }
            false -> {
                gotoSettingPermission(application, Settings.ACTION_USAGE_ACCESS_SETTINGS)
            }
        }
    }

    fun getTimeFlow(): Flow<Pair<String, String>> = flow {
        while (true) {
            val formattedTime: String = timeFormat.format(Date()).toString()
            val formattedAmPm: String = amPmFormat.format(Date()).toString()
            emit(Pair(formattedTime, formattedAmPm))
            delay(1000)
        }
    }

    fun getDate() = flow {
        emit("")
        val date = Date()
        val tDay = dayFormat.format(date).toString().capitalise()
        val tDate = dateFormat.format(date).run {
            ordinalOf(this.toInt())
        }
        val tMonth = monthFormat.format(date).toString().capitalise()
        emit("$tDay, $tDate $tMonth")
    }

    private fun fetchUsageStats(context: Context) {
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        val queryUsageStats: List<UsageStats> = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            cal.timeInMillis,
            System.currentTimeMillis()
        )
        appUsageList = queryUsageStats.toAppUsageStatsList() as MutableList<AppUsageStats>
    }

    private fun getAppList(context: Context) {
        val pm: PackageManager = context.packageManager
        val i = Intent(Intent.ACTION_MAIN, null)
        i.addCategory(Intent.CATEGORY_LAUNCHER)
        val allApps = pm.queryIntentActivities(i, 0)
        for (ri in allApps) {
            val app = AppInfo()
            app.appName = ri.loadLabel(pm).toString()
            app.packageName = ri.activityInfo.packageName
            app.appIcon = ri.activityInfo.loadIcon(pm)
            appsList.add(app)
        }
    }

    private fun gotoSettingPermission(context: Context, code: String) {
        val intent = Intent(code)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        context.startActivity(intent)
    }


    private fun checkUsagePermission(context: Context): Boolean {
        val appOpsManager: AppOpsManager?
        appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE)!! as AppOpsManager
        val mode: Int = appOpsManager.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(), context.packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

}