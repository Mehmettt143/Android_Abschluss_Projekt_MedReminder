package com.example.medreminder.ui.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingViewModel : ViewModel() {

    private val _notificationTime = MutableStateFlow("") //
    val notificationTime = _notificationTime.asStateFlow()


    fun updateNotificationTime(time: String) {
        _notificationTime.value = time
    }
}