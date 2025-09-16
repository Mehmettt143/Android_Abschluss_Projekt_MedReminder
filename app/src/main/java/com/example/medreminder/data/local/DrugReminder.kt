package com.example.medreminder.data.local

data class DrugReminder(
    val id: String,
    val brandName: String,
    val dosage: String,
    val selectedTime: String,
    val timeHour: String,
    val timeMinute: String
)
