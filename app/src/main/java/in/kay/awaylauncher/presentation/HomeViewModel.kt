package `in`.kay.awaylauncher.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    fun getTimeFlow() : Flow<Pair<String,String>> = flow {
        while (true) {
            val timeFormat = SimpleDateFormat("hh:mm")
            val amPmFormat = SimpleDateFormat("aa")
            val formattedTime: String = timeFormat.format(Date()).toString()
            val formattedAmPm: String = amPmFormat.format(Date()).toString()
            emit(Pair(formattedTime,formattedAmPm))
            delay(1000)
        }
    }
}