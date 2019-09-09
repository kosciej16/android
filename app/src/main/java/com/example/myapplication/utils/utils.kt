package com.example.myapplication.utils

fun Any?.toStringOrEmpty() = this?.toString() ?: ""

fun String?.toIntOrZero() = this?.toIntOrNull() ?: 0
