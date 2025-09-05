package com.example.medreminder.utils

object TimeHelper {

    // Helper function
    internal fun validateTimeInput(newValue: String, range: IntRange): String {
        return if (newValue.length <= 2 && (newValue.isEmpty() || newValue.toIntOrNull()?.let { it in range } == true)) {
            if (newValue.isNotEmpty() && newValue.length == 1) {
                newValue.padStart(2, '0')
            } else {
                newValue
            }
        } else {
            newValue.take(2)
        }
    }
}