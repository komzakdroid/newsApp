package com.limuealimi.newsapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
val yyyy_mm_dd_hh_mm_ss_z = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

@SuppressLint("ConstantLocale")
val yyyy_mm_dd = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

@SuppressLint("ConstantLocale")
val hh_mm = SimpleDateFormat("HH:mm", Locale.getDefault())

@SuppressLint("ConstantLocale")
val ee_mmm_dd = SimpleDateFormat("EE, MMM dd", Locale.getDefault())

@SuppressLint("ConstantLocale")
val eeee_mmmm_dd = SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault())